package com.example.telphone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class FirsrtActivity extends AppCompatActivity {
    TelephonyManager telMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsrt);

        telMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telMgr.listen(new CallStateListener(), CallStateListener.LISTEN_CALL_STATE);



    }

    /**
     * 监视电话状态
     *
     * @author GV
     */
    public class CallStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_IDLE)//挂断
            {
                Log.e("IDLE", incomingNumber);
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK)//接听
            {
                Log.e("OFFHOOK", incomingNumber);
            } else if (state == TelephonyManager.CALL_STATE_RINGING)//来电
            {
                try {
                    PhoneUtils.getITelephony(telMgr).endCall();//挂断
                    PhoneUtils.getITelephony(telMgr).cancelMissedCallsNotification();//取消未接显示
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }
}
