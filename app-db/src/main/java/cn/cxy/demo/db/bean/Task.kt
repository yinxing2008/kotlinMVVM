package cn.cxy.demo.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(@PrimaryKey(autoGenerate = true) val id: Int,
                var name: String)