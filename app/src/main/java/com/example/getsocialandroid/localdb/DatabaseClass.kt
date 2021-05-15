package com.example.getsocialandroid.localdb
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.getsocialandroid.cards.Rest

@Database(entities = [User::class, RestDb::class], version = 1, exportSchema = false)
abstract class DatabaseClass: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun restDao(): RestDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseClass? = null

        fun getDatabase(context: Context): DatabaseClass{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseClass::class.java,
                    "dbNeredeYesem"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}