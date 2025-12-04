package com.plcoding.cryptotracker.crypto.presenatation.coin_list

import com.plcoding.cryptotracker.crypto.presenatation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coin: CoinUi): CoinListAction
}