package com.wopnersoft.unitconverter.plus.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// compiled from: ProGuard
class n extends SQLiteOpenHelper {
    n(Context context) {
        super(context, "currency_db", null, 9);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AED','Arab Emirates Dirham')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AFN','Afghan Afghani')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ALL','Albanian Lek')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AMD','Armenian Dram')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ANG','Netherlands Antillean Guilder')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AOA','Angolan Kwanza')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ARS','Argentine Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AUD','Australian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AWG','Aruban Guilder')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('AZN','Azerbaijani Manat')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BAM','Marka')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BBD','Barbados Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BDT','Bangladeshi Taka')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BGN','Bulgarian Lev')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BHD','Bahraini Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BIF','Burundi Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BMD','Bermudian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BND','Brunei Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BOB','Boliviano')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BRL','Brazilian Real')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BSD','Bahamian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BTC','Bitcoin')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BTN','Bhutan Ngultrum')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BWP','Botswana Pula')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BYR','Belarusian Ruble')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('BZD','Belize Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CAD','Canadian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CDF','Francs')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CHF','Swiss Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CLF','Chilean Unidad de Fomento')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CLP','Chilean Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CNH','Chinese Offshore Yuan')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CNY','Yuan Renminbi')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('COP','Colombian Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CRC','Costa Rican Colon')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CUP','Cuban Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CVE','Cape Verde Escudo')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('CZK','Czech Koruna')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('DJF','Djibouti Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('DKK','Danish Krone')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('DOP','Dominican Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('DZD','Algerian Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('EGP','Egyptian Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ETB','Ethiopian Birr')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('EUR','Euro')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('FJD','Fiji Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('FKP','Falkland Islands Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GBP','Pound Sterling')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GEL','Georgian Lari')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GHS','Ghanaian Cedi')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GIP','Gibraltar Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GMD','Gambian Dalasi')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GNF','Guinea Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GTQ','Guatemalan Quetzal')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('GYD','Guyana Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('HKD','Hong Kong Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('HNL','Honduran Lempira')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('HRK','Croatian Kuna')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('HTG','Haitian Gourde')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('HUF','Hungarian Forint')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('IDR','Indonesian Rupiah')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('IEP','Irish Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ILS','Israeli New Shekel')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('INR','Indian Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('IQD','Iraqi Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('IRR','Iranian Rial')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ISK','Iceland Krona')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('JMD','Jamaican Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('JOD','Jordanian Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('JPY','Japanese Yen')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KES','Kenyan Shilling')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KGS','Kyrgyzstani Som')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KHR','Kampuchean Riel')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KMF','Comoros Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KPW','North Korean Won')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KRW','Korean Won')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KWD','Kuwaiti Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('KZT','Kazakhstan Tenge')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LAK','Lao Kip')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LBP','Lebanese Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LKR','Sri Lanka Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LRD','Liberian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LSL','Lesotho Loti')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LTL','Lithuanian Litas')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LVL','Latvian Lats')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('LYD','Libyan Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MAD','Moroccan Dirham')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MDL','Moldovan Leu')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MGA','Malagasy Ariary')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MKD','Macedonia Denar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MMK','Myanmar Kyat')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MNT','Mongolian Tugrik')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MOP','Macau Pataca')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MRO','Mauritanian Ouguiya')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MUR','Mauritius Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MVR','Maldive Rufiyaa')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MWK','Malawi Kwacha')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MXN','Mexican Nuevo Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MYR','Malaysian Ringgit')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('MZN','New Mozambican Metical')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NAD','Namibian Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NGN','Nigerian Naira')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NIO','Nicaraguan Cordoba Oro')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NOK','Norwegian Krone')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NPR','Nepalese Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('NZD','New Zealand Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('OMR','Omani Rial')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PAB','Panamanian Balboa')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PEN','Peruvian Nuevo Sol')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PGK','Papua New Guinea Kina')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PHP','Philippine Peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PKR','Pakistan Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PLN','Polish Zloty')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('PYG','Paraguay Guarani')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('QAR','Qatari Rial')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('RON','Romanian Leu')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('RSD','Serbian Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('RUB','Russian Ruble')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('RWF','Rwanda Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SAR','Saudi Riyal')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SBD','Solomon Islands Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SCR','Seychelles Rupee')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SDG','Sudanese Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SEK','Swedish Krona')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SGD','Singapore Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SHP','St. Helena Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SLL','Sierra Leone Leone')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SOS','Somali Shilling')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SRD','Suriname Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('STD','Sao Tome Dobra')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SVC','El Salvador Colon')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SYP','Syrian Pound')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('SZL','Swaziland Lilangeni')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('THB','Thai Baht')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TJS','Tajikistan Somoni')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TMT','Turkmenistan Manat')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TND','Tunisian Dinar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TOP','Tongan Pa''anga')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TRY','Turkish Lira')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TTD','Trinidad and Tobago Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TWD','Taiwan Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('TZS','Tanzanian Shilling')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('UAH','Ukrainian Hryvnia')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('UGX','Uganda Shilling')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('USD','US Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('UYU','Uruguayan peso')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('UZS','Uzbekistan Sum')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('VEF','Venezuelan Bolivar Fuerte')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('VND','Vietnamese Dong')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('VUV','Vanuatu Vatu')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('WST','Samoan Tala')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XAF','CFA Franc BEAC')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XAG','Silver Ounce')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XAU','Gold Ounce')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XCD','East Caribbean Dollar')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XOF','CFA Franc BCEAO')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XCP','Copper Ounce')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XPD','Palladium Ounce')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XPF','CFP Franc')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('XPT','Platinum Ounce')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('YER','Yemeni Rial')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ZAR','South African Rand')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ZMK','Zambian Kwacha')");
        sQLiteDatabase.execSQL("INSERT INTO currency(currency_country,currency_country_desc) VALUES('ZMW','Zambian Kwacha')");
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table currency (_id integer primary key autoincrement, currency_country text not null, currency_country_desc text not null, currency_value real null);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w("CurrencyDbAdapter", new StringBuilder("Upgrading database from version ").append(i).append(" to ").append(i2).append(", which will destroy all old data").toString());
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS currency");
        onCreate(sQLiteDatabase);
    }
}