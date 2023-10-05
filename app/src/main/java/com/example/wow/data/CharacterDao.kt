package com.example.wow.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Delete
    fun delete(character: Character)

    @Insert
    fun create(character: Character) : Long

    @Update
    fun update(character: Character)

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getById(characterId: Int): Character
}