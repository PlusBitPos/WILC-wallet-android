package io.horizontalsystems.bankwallet.modules.balance

import java.math.BigDecimal

class BalanceSorter : BalanceModule.IBalanceSorter {

    private val wilcTokenCode = "WILC"

    override fun sort(items: List<BalanceModule.BalanceItem>, sortType: BalanceSortType): List<BalanceModule.BalanceItem> {
        return when (sortType) {
            BalanceSortType.Value -> sortByBalance(items)
            BalanceSortType.Name -> items.sortedBy { it.wallet.coin.title }
            BalanceSortType.PercentGrowth -> items.sortedByDescending { it.marketInfo?.rateDiff }
        }.sortedWith(defaultToTop)
    }

    private val defaultToTop = Comparator<BalanceModule.BalanceItem> { a, b ->
        when {
            a.wallet.coin.coinId == wilcTokenCode -> -1
            b.wallet.coin.coinId == wilcTokenCode -> 1
            else -> 0
        }
    }

    private fun sortByBalance(items: List<BalanceModule.BalanceItem>): List<BalanceModule.BalanceItem> {
        val comparator =
                compareByDescending<BalanceModule.BalanceItem> {
                    it.balance ?: BigDecimal.ZERO > BigDecimal.ZERO
                }.thenByDescending {
                    it.fiatValue ?: BigDecimal.ZERO > BigDecimal.ZERO
                }.thenByDescending {
                    it.fiatValue
                }.thenByDescending {
                    it.balance
                }.thenBy {
                    it.wallet.coin.title
                }

        return items.sortedWith(comparator)
    }
}
