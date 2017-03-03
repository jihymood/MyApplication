package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FirstActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //判断是否支持BLE特性
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            initBluetoothBle();
        } else {
            showToast("this device can not support Bluetooth BLE");
        }
    }

    private static final int REQUEST_CODE_BLUETOOTH_ENABLE = 1;
    private static final int SCAN_PERIOD = 10000;

    private BluetoothAdapter bluetoothAdapter;
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            //Batch 一批
        }

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            switch (callbackType) {
                case ScanSettings.CALLBACK_TYPE_ALL_MATCHES:
                    break;
                case ScanSettings.CALLBACK_TYPE_FIRST_MATCH:
                    break;
                case ScanSettings.CALLBACK_TYPE_MATCH_LOST:
                    break;
            }
            BluetoothDevice device = result.getDevice();
            String deviceName = device.getName();
            String deviceAddress = device.getAddress();
            int deviceType = device.getType();
            ParcelUuid[] deviceUuids = device.getUuids();
            int deviceBondState = device.getBondState();

            ScanRecord scanRecord = result.getScanRecord();
            int advertiseFlags = scanRecord.getAdvertiseFlags();
            //txPowerLevel 发射功率等级
            //URL：http://www.bianceng.cn/OS/extra/201608/50410.htm
            int txPowerLevel = scanRecord.getTxPowerLevel();
            String recordDeviceName = scanRecord.getDeviceName();
            List<ParcelUuid> serviceUuids = scanRecord.getServiceUuids();
            Map<ParcelUuid, byte[]> serviceData = scanRecord.getServiceData();

            //RSSI 信号强度,可以用来测算距离
            int rssi = result.getRssi();
            long timestampNanos = result.getTimestampNanos();


            int startByte = 2;
            boolean patternFound = false;
            // 寻找ibeacon
//            while (startByte <= 5) {
//                if (((int) scanRecord[startByte + 2] & 0xff) == 0x02 && // Identifies
//                        // an
//                        // iBeacon
//                        ((int) scanRecord[startByte + 3] & 0xff) == 0x15) { // Identifies
//                    // correct
//                    // data
//                    // length
//                    patternFound = true;
//                    break;
//                }
//                startByte++;
//            }

            // 转换为16进制
            byte[] uuidBytes = new byte[16];
//            byte[] uuidBytes = {12, 14, 0, -111, 70, 20, -18, -7, 58, 116, -8, -38, 63, 23, 4, -8};
            String hexString = bytesToHex(uuidBytes);

            // ibeacon的UUID值
            String uuid = hexString.substring(0, 8) + "-"
                    + hexString.substring(8, 12) + "-"
                    + hexString.substring(12, 16) + "-"
                    + hexString.substring(16, 20) + "-"
                    + hexString.substring(20, 32);
            Log.e("uuid", uuid);

        }

        @Override
        public void onScanFailed(int errorCode) {

        }
    };


    static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    private void initBluetoothBle() {
        //BluetoothAdapter是Android系统中所有蓝牙操作都需要的，它对应本地Android设备的蓝牙模块，在整个系统中BluetoothAdapter是单例的。当你获取到它的示例之后，就能进行相关的蓝牙操作了。
        //BluetoothManager在Android4.3以上支持(API level 18)
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        Log.e("FirstActivity", uuid.toString());
        requestEnable();
    }

    /**
     * 开启蓝牙
     * 打开和关闭蓝牙模块, 都可以通过ACTION_STATE_CHANGED广播来监听
     */
    private void requestEnable() {
        //第一种方法打开蓝牙, 没有任何提示, 直接就打开了
        //boolean result = bluetoothAdapter.enable();
        //第二种方法发送广播, 会弹出一个对话框, 选择是否打开蓝牙, 选择是蓝牙才打开;
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_BLUETOOTH_ENABLE);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            bluetoothAdapter.startLeScan(mLeScanCallback);
//        }
        final BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
        //scan分为2类,而在android L之前,搜索条件只有uuid
        //(1)直接搜索全部周围peripheral(外围的)设备,搜索结果将通过这个callback返回
        scanner.startScan(scanCallback);
    }

    /**
     * 扫描BLE设备.注意该方法无法扫描标准蓝牙,只能扫描BLE设备
     */
    private void scanBleDevice(final boolean enabled) {
        if (bluetoothAdapter == null) {
            return;
        }
        /*
        为什么不能再使用单例的BluetoothAdapter? 原因如下:
        bluetoothAdapter.startLeScan() //deprecated
        http://stackoverflow.com/questions/30223071/startlescan-replacement-to-current-api
        Remember that the method: public BluetoothLeScanner getBluetoothLeScanner () isn't static.
        If you do: BluetoothAdapter.getBluetoothLeScanner()
        you will get an error, since getDefaultAdapter() is a static method, but getBluetoothLeScanner() isn't.
        You need an instance of a BluetoothAdapter.
         */
        final BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
        if (enabled) {
            //scan分为2类,而在android L之前,搜索条件只有uuid
            //(1)直接搜索全部周围peripheral(外围的)设备,搜索结果将通过这个callback返回
            scanner.startScan(scanCallback);
            //(2)根据过滤条件搜索设备
            final List<ScanFilter> scanFilters = new ArrayList<ScanFilter>();
            //uuid格式8-4-4-4-12(32位,128bit)
            //address格式(12位,48bit)
            scanFilters.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString
                    ("00000000-0000-0000-0000-000000000000")).setDeviceAddress("00:00:00:00:00:00").build());
            ScanSettings scanSettings = new ScanSettings.Builder()
                    //require API 23
                    //.setCallbackType(0).setMatchMode(0).setNumOfMatches(0)
                    .setReportDelay(0).setScanMode(BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE).build();
            scanner.startScan(scanFilters, scanSettings, scanCallback);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanner.stopScan(scanCallback);
                }
            }, SCAN_PERIOD);
        } else {
            scanner.stopScan(scanCallback);
        }
    }

    /**
     * 两个设备通过BLE通信，首先需要建立GATT连接。这里我们讲的是Android设备作为client端，连接GATT Server。数据发送方向总是从server推送到client
     */
    private void connectToGATTServer(BluetoothDevice device) {
        //函数成功，返回BluetoothGatt对象，它是GATT profile的封装。通过这个对象，我们就能进行GATT Client端的相关操作。BluetoothGattCallback用于传递一些连接状态及结果。
        BluetoothGatt bluetoothGatt = device.connectGatt(this, false, gattCallback);

        //连接远程设备
        boolean connectResult = bluetoothGatt.connect();
        //搜索连接设备所支持的service
        boolean discoverResult = bluetoothGatt.discoverServices();
        //断开与远程设备的GATT连接
        bluetoothGatt.disconnect();
        //关闭GATT Client端
        bluetoothGatt.close();
        //读取指定的characteristic。
        //boolean readResult = bluetoothGatt.readCharacteristic(characteristic);
        //设置当指定characteristic值变化时，发出通知
        //boolean setResult = bluetoothGatt.setCharacteristicNotification(characteristic, enabled);
        //获取远程设备所支持的services
        List<BluetoothGattService> gattServices = bluetoothGatt.getServices();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }
    };
}
