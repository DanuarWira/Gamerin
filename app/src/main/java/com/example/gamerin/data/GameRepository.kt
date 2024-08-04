package com.example.gamerin.data

import com.example.gamerin.model.GameCart
import com.example.gamerin.model.GamesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GameRepository {

    private val gameCarts = mutableListOf<GameCart>()

    init {
        if(gameCarts.isEmpty()) {
            GamesDataSource.dummyGames.forEach {
                gameCarts.add(GameCart(it, 0))
            }
        }
    }

    fun getAllGames(): Flow<List<GameCart>> {
        return flowOf(gameCarts)
    }

    fun getGameCartById(gameId: Int): GameCart {
        return gameCarts.first {
            it.game.id == gameId
        }
    }

    fun updateGameCart(gameId: Int, newPriceValue: Int): Flow<Boolean> {
        val index = gameCarts.indexOfFirst { it.game.id == gameId }
        val result = if (index >= 0) {
            val gameCart = gameCarts[index]
            gameCarts[index] = gameCart.copy(game = gameCart.game, count = newPriceValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedGameCart(): Flow<List<GameCart>> {
        return getAllGames()
            .map {
                it.filter {
                    it.count != 0
                }
            }
    }

    fun removeAddedGameCart() {

    }

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(): GameRepository =
            instance ?: synchronized(this) {
                GameRepository().apply {
                    instance = this
                }
            }
    }
}