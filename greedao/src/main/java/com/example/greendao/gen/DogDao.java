package com.example.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.greedao.bean.Dog;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOG".
*/
public class DogDao extends AbstractDao<Dog, Long> {

    public static final String TABLENAME = "DOG";

    /**
     * Properties of entity Dog.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Height = new Property(1, int.class, "height", false, "HEIGHT");
        public final static Property Color = new Property(2, String.class, "color", false, "COLOR");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
    };


    public DogDao(DaoConfig config) {
        super(config);
    }
    
    public DogDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"HEIGHT\" INTEGER NOT NULL ," + // 1: height
                "\"COLOR\" TEXT," + // 2: color
                "\"NAME\" TEXT);"); // 3: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Dog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getHeight());
 
        String color = entity.getColor();
        if (color != null) {
            stmt.bindString(3, color);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Dog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getHeight());
 
        String color = entity.getColor();
        if (color != null) {
            stmt.bindString(3, color);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Dog readEntity(Cursor cursor, int offset) {
        Dog entity = new Dog( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // height
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // color
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Dog entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHeight(cursor.getInt(offset + 1));
        entity.setColor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Dog entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Dog entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
