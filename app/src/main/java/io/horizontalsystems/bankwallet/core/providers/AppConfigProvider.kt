package io.horizontalsystems.bankwallet.core.providers

import io.horizontalsystems.bankwallet.BuildConfig
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.core.App
import io.horizontalsystems.bankwallet.core.IAppConfigProvider
import io.horizontalsystems.bankwallet.entities.*
import io.horizontalsystems.core.IBuildConfigProvider
import io.horizontalsystems.core.ILanguageConfigProvider
import io.horizontalsystems.core.entities.Currency
import java.math.BigDecimal

class AppConfigProvider : IAppConfigProvider, ILanguageConfigProvider, IBuildConfigProvider {

    override val companyWebPageLink: String = "https://horizontalsystems.io"
    override val appWebPageLink: String = "https://unstoppable.money"
    override val appGithubLink: String = "https://github.com/horizontalsystems/unstoppable-wallet-android"
    override val companyTwitterLink: String = "https://twitter.com/UnstoppableByHS"
    override val companyTelegramLink: String = "https://t.me/unstoppable_announcements"
    override val companyRedditLink: String = "https://reddit.com/r/UNSTOPPABLEWallet/"
    override val btcCoreRpcUrl: String = "https://btc.horizontalsystems.xyz/rpc"
    override val reportEmail = "info@plusbit.tech"
    override val walletHelpTelegramGroup = "PlusBitWallet"

    override val cryptoCompareApiKey = App.instance.getString(R.string.cryptoCompareApiKey)
    override val infuraProjectId = App.instance.getString(R.string.infuraProjectId)
    override val infuraProjectSecret = App.instance.getString(R.string.infuraSecretKey)
    override val etherscanApiKey = App.instance.getString(R.string.etherscanKey)
    override val guidesUrl = App.instance.getString(R.string.guidesUrl)
    override val faqUrl = App.instance.getString(R.string.faqUrl)
    override val fiatDecimal: Int = 2
    override val maxDecimal: Int = 8
    override val feeRateAdjustForCurrencies: List<String> = listOf("USD","EUR")

    override val currencies: List<Currency> = listOf(
            Currency(code = "USD", symbol = "\u0024", decimal = 2),
            Currency(code = "EUR", symbol = "\u20AC", decimal = 2),
            Currency(code = "GBP", symbol = "\u00A3", decimal = 2),
            Currency(code = "JPY", symbol = "\u00A5", decimal = 2)
    )

    override val featuredCoins: List<Coin>
        get() = listOf(
                defaultCoins[0],
                defaultCoins[1],
                defaultCoins[2],
                defaultCoins[3],
                defaultCoins[4],
                defaultCoins[5],
                defaultCoins[6],
        )

    override val ethereumCoin: Coin
        get() = defaultCoins[3]

    override val defaultCoins: List<Coin> by lazy {
        listOf(
                Coin("WILC", "Wrapped ILCoin", "WILC", 8, CoinType.Erc20("0xc98a910ede52e7d5308525845f19e17470dbccf7")),
                Coin("WETH", "Wrapped Ethereum", "WETH", 18, CoinType.Erc20("0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2")),
                Coin("WBTC", "Wrapped Bitcoin", "WBTC", 8, CoinType.Erc20("0x2260fac5e5542a773aa44fbcfedf7c193bc2c599")),
                Coin("ETH", "Ethereum", "ETH", 18, CoinType.Ethereum),
                Coin("USDT", "Tether USD", "USDT", 6, CoinType.Erc20("0xdAC17F958D2ee523a2206206994597C13D831ec7")),
                Coin("DAI", "Dai", "DAI", 18, CoinType.Erc20("0x6b175474e89094c44da98b954eedeac495271d0f")),
                Coin("BUSD", "Binance USD", "BUSD", 18, CoinType.Erc20("0x4fabb145d64652a948d72533023f6e7a623c7c53")),
        )
    }


    //  ILanguageConfigProvider

    override val localizations: List<String>
        get() {
            val coinsString = "de,en,es,fa,fr,ko,ru,tr,zh"
            return coinsString.split(",")
        }

    //  IBuildConfigProvider

    override val testMode: Boolean = BuildConfig.testMode

    override val skipRootCheck: Boolean = BuildConfig.skipRootCheck

}
