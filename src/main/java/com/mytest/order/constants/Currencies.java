package com.mytest.order.constants;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum representing various currencies with their descriptions. Each currency
 * is associated with a string that describes the country and the currency name.
 */
public enum Currencies {

	// Enum constants (each represents a country-currency pair)
	/** Afghanistan - Afghani */
	AFGANISTAN("Afghanistan-Afghani"),

	/** Albania - Lek */
	ALBANIA("Albania-Lek"),

	/** Algeria - Dinar */
	ALGERIA("Algeria-Dinar"),

	/** Angola - Kwanza */
	ANGOLA("Angola-Kwanza"),

	/** Antigua & Barbuda - East Caribbean Dollar */
	ANTIGUA_AND_BARBUDA("Antigua & Barbuda-East Caribbean Dollar"),

	/** Argentina - Peso */
	ARGENTINA("Argentina-Peso"),

	/** Armenia - Dram */
	ARMENIA("Armenia-Dram"),

	/** Australia - Dollar */
	AUSTRALIA("Australia-Dollar"),

	/** Austria - Euro */
	AUSTRIA("Austria-Euro"),

	/** Azerbaijan - Manat */
	AZERBAIJAN("Azerbaijan-Manat"),

	/** Bahamas - Dollar */
	BAHAMAS("Bahamas-Dollar"),

	/** Bahrain - Dinar */
	BAHRAIN("Bahrain-Dinar"),

	/** Bangladesh - Taka */
	BANGLADESH("Bangladesh-Taka"),

	/** Barbados - Dollar */
	BARBADOS("Barbados-Dollar"),

	/** Belarus - New Ruble */
	BELARUS("Belarus-New Ruble"),

	/** Belgium - Euro */
	BELGIUM("Belgium-Euro"),

	/** Belize - Dollar */
	BELIZE("Belize-Dollar"),

	/** Benin - Cfa Franc */
	BENIN("Benin-Cfa Franc"),

	/** Bermuda - Dollar */
	BERMUDA("Bermuda-Dollar"),

	/** Bolivia - Boliviano */
	BOLIVIA("Bolivia-Boliviano"),

	/** Bosnia - Marka */
	BOSNIA("Bosnia-Marka"),

	/** Botswana - Pula */
	BOTSWANA("Botswana-Pula"),

	/** Brazil - Real */
	BRAZIL("Brazil-Real"),

	/** Brunei - Dollar */
	BRUNEI("Brunei-Dollar"),

	/** Bulgaria - Lev New */
	BULGARIA("Bulgaria-Lev New"),

	/** Burkina Faso - Cfa Franc */
	BURKINA_FASO("Burkina Faso-Cfa Franc"),

	/** Burundi - Franc */
	BURUNDI("Burundi-Franc"),

	/** Cambodia - Riel */
	CAMBODIA("Cambodia-Riel"),

	/** Cameroon - Cfa Franc */
	CAMEROON("Cameroon-Cfa Franc"),

	/** Canada - Dollar */
	CANADA("Canada-Dollar"),

	/** Cape Verde - Escudo */
	CAPE_VERDE("Cape Verde-Escudo"),

	/** Cayman Islands - Dollar */
	CAYMAN_ISLANDS("Cayman Islands-Dollar"),

	/** Central African Republic - Cfa Franc */
	CENTRAL_AFRICAN_REPUBLIC("Central African Republic-Cfa Franc"),

	/** Chad - Cfa Franc */
	CHAD("Chad-Cfa Franc"),

	/** Chile - Peso */
	CHILE("Chile-Peso"),

	/** China - Renminbi */
	CHINA("China-Renminbi"),

	/** Colombia - Peso */
	COLUMBIA("Colombia-Peso"),

	/** Comoros - Franc */
	COMOROS("Comoros-Franc"),

	/** Congo - Cfa Franc */
	CONGO("Congo-Cfa Franc"),

	/** Costa Rica - Colon */
	COSTA_RICA("Costa Rica-Colon"),

	/** Cote D'Ivoire - Cfa Franc */
	COTE_D_IVOIRE("Cote D'Ivoire-Cfa Franc"),

	/** Croatia - Euro */
	CROATIA("Croatia-Euro"),

	/** Croatia - Kuna */
	CROATIA_KUNA("Croatia-Kuna"),

	/** Cross Border - Euro */
	CROSS_BORDER("Cross Border-Euro"),

	/** Cuba - Chavito */
	CUBA_CHAVITO("Cuba-Chavito"),

	/** Cuba - Peso */
	CUBA_PESO("Cuba-Peso"),

	/** Cyprus - Euro */
	CYPRUS("Cyprus-Euro"),

	/** Czech Republic - Koruna */
	CZECH_REPUBLIC("Czech Republic-Koruna"),

	/** Democratic Republic Of Congo - Congolese Franc */
	DEMOCRATIC_REPUBLIC_OF_CONGO("Democratic Republic Of Congo-Congolese Franc"),

	/** Denmark - Krone */
	DENMARK("Denmark-Krone"),

	/** Djibouti - Franc */
	DJIBOUTI("Djibouti-Franc"),

	/** Dominican Republic - Peso */
	DOMINICAN_REPUBLIC("Dominican Republic-Peso"),

	/** Ecuador - Dolares */
	ECUADOR("Ecuador-Dolares"),

	/** Egypt - Pound */
	EGYPT("Egypt-Pound"),

	/** El Salvador - Dollar */
	EL_SALVADOR("El Salvador-Dollar"),

	/** Equatorial Guinea - Cfa Franc */
	EQUATORIAL_GUINEA("Equatorial Guinea-Cfa Franc"),

	/** Eritrea - Nakfa */
	ERITREA("Eritrea-Nakfa"),

	/** Eritrea - Nakfa Salary Payment */
	ERITREA_SALARY_PAYMENT("Eritrea-Nakfa Salary Payment"),

	/** Estonia - Euro */
	ESTONIA("Estonia-Euro"),

	/** Eswatini - Lilangeni */
	ESWATINI("Eswatini-Lilangeni"),

	/** Ethiopia - Birr */
	ETHIOPIA("Ethiopia-Birr"),

	/** Euro Zone - Euro */
	EURO_ZONE("Euro Zone-Euro"),

	/** Fiji - Dollar */
	FIJI("Fiji-Dollar"),

	/** Finland - Euro */
	FINLAND("Finland-Euro"),

	/** France - Euro */
	FRANCE("France-Euro"),

	/** Gabon - Cfa Franc */
	GABON("Gabon-Cfa Franc"),

	/** Gambia - Dalasi */
	GAMBIA("Gambia-Dalasi"),

	/** Georgia - Lari */
	GEORGIA("Georgia-Lari"),

	/** Germany - Euro */
	GERMANY("Germany-Euro"),

	/** Ghana - Cedi */
	GHANA("Ghana-Cedi"),

	/** Ghana - Cedi */
	GHANA_CEDI("Ghana-Cedi"),

	/** Greece - Euro */
	GREECE("Greece-Euro"),

	/** Grenada - East Caribbean Dollar */
	GRENADA("Grenada-East Caribbean Dollar"),

	/** Guatemala - Quetzal */
	GUATEMALA("Guatemala-Quetzal"),

	/** Guinea Bissau - Cfa Franc */
	GUINEA_BISSAU("Guinea Bissau-Cfa Franc"),

	/** Guinea - Franc */
	GUINEA("Guinea-Franc"),

	/** Guyana - Dollar */
	GUYANA("Guyana-Dollar"),

	/** Haiti - Gourde */
	HAITI("Haiti-Gourde"),

	/** Honduras - Lempira */
	HONDURAS("Honduras-Lempira"),

	/** Hong Kong - Dollar */
	HONG_KONG("Hong Kong-Dollar"),

	/** Hungary - Forint */
	HUNGARY("Hungary-Forint"),

	/** Iceland - Krona */
	ICELAND("Iceland-Krona"),

	/** India - Rupee */
	INDIA("India-Rupee"),

	/** Indonesia - Rupiah */
	INDONESIA("Indonesia-Rupiah"),

	/** Iran - Rial */
	IRAN("Iran-Rial"),

	/** Iraq - Dinar */
	IRAQ("Iraq-Dinar"),

	/** Ireland - Euro */
	IRELAND("Ireland-Euro"),

	/** Israel - Shekel */
	ISRAEL("Israel-Shekel"),

	/** Italy - Euro */
	ITALY("Italy-Euro"),

	/** Jamaica - Dollar */
	JAMAICA("Jamaica-Dollar"),

	/** Japan - Yen */
	JAPAN("Japan-Yen"),

	/** Jordan - Dinar */
	JORDAN("Jordan-Dinar"),

	/** Kazakhstan - Tenge */
	KAZAKHSTAN("Kazakhstan-Tenge"),

	/** Kenya - Shilling */
	KENYA("Kenya-Shilling"),

	/** Korea - Won */
	KOREA("Korea-Won"),

	/** Kosovo - Euro */
	KOSOVO("Kosovo-Euro"),

	/** Kuwait - Dinar */
	KUWAIT("Kuwait-Dinar"),

	/** Kyrgyzstan - Som */
	KYRGYZSTAN("Kyrgyzstan-Som"),

	/** Laos - Kip */
	LAOS("Laos-Kip"),

	/** Latvia - Euro */
	LATVIA("Latvia-Euro"),

	/** Lebanon - Pound */
	LEBANON("Lebanon-Pound"),

	/** Lesotho - Maloti */
	LESOTHO("Lesotho-Maloti"),

	/** Liberia - Dollar */
	LIBERIA("Liberia-Dollar"),

	/** Libya - Dinar */
	LIBYA("Libya-Dinar"),

	/** Libya - Dinar */
	LIBYA_DINAR("Libya-Dinar"),

	/** Lithuania - Euro */
	LITHUANIA("Lithuania-Euro"),
	/** Luxembourg - Euro */

	LUXEMBOURG("Luxembourg-Euro"),

	/** Madagascar - Ariary */
	MADAGASCAR("Madagascar-Ariary"),

	/** Malawi - Kwacha */
	MALAWI("Malawi-Kwacha"),

	/** Malaysia - Ringgit */
	MALAYSIA("Malaysia-Ringgit"),

	/** Maldives - Rufiyaa */
	MALDIVES("Maldives-Rufiyaa"),

	/** Mali - Cfa Franc */
	MALI("Mali-Cfa Franc"),

	/** Malta - Euro */
	MALTA("Malta-Euro"),

	/** Marshall Islands - U.S. Dollar */
	MARSHALL_ISLANDS("Marshall Islands-U.S. Dollar"),

	/** Mauritania - Ouguiya */
	MAURITANIA("Mauritania-Ouguiya"),

	/** Mauritius - Rupee */
	MAURITIUS("Mauritius-Rupee"),

	/** Mexico - Peso */
	MEXICO("Mexico-Peso"),

	/** Micronesia - U.S. Dollar */
	MICRONESIA("Micronesia-U.S. Dollar"),

	/** Moldova - Leu */
	MOLDOVA("Moldova-Leu"),

	/** Mongolia - Tugrik */
	MONGOLIA("Mongolia-Tugrik"),

	/** Montenegro - Euro */
	MONTENEGRO("Montenegro-Euro"),

	/** Morocco - Dirham */
	MOROCCO("Morocco-Dirham"),

	/** Mozambique - Metical */
	MOZAMBIQUE("Mozambique-Metical"),

	/** Myanmar - Kyat */
	MYANMAR("Myanmar-Kyat"),

	/** Namibia - Dollar */
	NAMIBIA("Namibia-Dollar"),

	/** Nepal - Rupee */
	NEPAL("Nepal-Rupee"),

	/** Netherlands Antilles - Guilder */
	NETHERLANDS_ANTILLES("Netherlands Antilles-Guilder"),

	/** Netherlands - Euro */
	NETHERLANDS("Netherlands-Euro"),
	/** New Zealand - Dollar */

	NEW_ZEALAND("New Zealand-Dollar"),

	/** Nicaragua - Cordoba */
	NICARAGUA("Nicaragua-Cordoba"),

	/** Niger - Cfa Franc */
	NIGER("Niger-Cfa Franc"),

	/** Nigeria - Naira */
	NIGERIA("Nigeria-Naira"),

	/** Norway - Krone */
	NORWAY("Norway-Krone"),

	/** Oman - Rial */
	OMAN("Oman-Rial"),

	/** Pakistan - Rupee */
	PAKISTAN("Pakistan-Rupee"),

	/** Palau - Dollar */
	PALAU("Palau-Dollar"),

	/** Panama - Balboa */
	PANAMA("Panama-Balboa"),

	/** Panama - Dolares */
	PANAMA_DOLARES("Panama-Dolares"),

	/** Papua New Guinea - Kina */
	PAPUA_NEW_GUINEA("Papua New Guinea-Kina"),

	/** Paraguay - Guarani */
	PARAGUAY("Paraguay-Guarani"),

	/** Peru - Sol */
	PERU("Peru-Sol"),

	/** Philippines - Peso */
	PHILIPPINES("Philippines-Peso"),

	/** Poland - Zloty */
	POLAND("Poland-Zloty"),

	/** Portugal - Euro */
	PORTUGAL("Portugal-Euro"),

	/** Qatar - Riyal */
	QATAR("Qatar-Riyal"),

	/** Republic Of North Macedonia - Denar */
	REP_OF_NORTH_MACEDONIA("Republic Of North Macedonia-Denar"),

	/** Romania - New Leu */
	ROMANIA("Romania-New Leu"),

	/** Russia - Ruble */
	RUSSIA("Russia-Ruble"),
	/** Rwanda - Franc */

	RWANDA("Rwanda-Franc"),

	/** Sao Tome & Principe - New Dobras */
	SAO_TOME_AND_PRINCIPE("Sao Tome & Principe-New Dobras"),

	/** Saudi Arabia - Riyal */
	SAUDI_ARABIA("Saudi Arabia-Riyal"),

	/** Senegal - Cfa Franc */
	SENEGAL("Senegal-Cfa Franc"),

	/** Serbia - Dinar */
	SERBIA("Serbia-Dinar"),

	/** Seychelles - Rupee */
	SEYCHELLES("Seychelles-Rupee"),

	/** Sierra Leone - Leone */
	SIERRA_LEONE("Sierra Leone-Leone"),

	/** Sierra Leone - Old Leone */
	SIERRA_LEONE_OLD("Sierra Leone-Old Leone"),

	/** Singapore - Dollar */
	SINGAPORE("Singapore-Dollar"),

	/** Slovakia - Euro */
	SLOVAKIA("Slovakia-Euro"),

	/** Slovenia - Euro */
	SLOVENIA("Slovenia-Euro"),

	/** Solomon Islands - Dollar */
	SOLOMON_ISLANDS("Solomon Islands-Dollar"),

	/** Somali - Shilling */
	SOMALI("Somali-Shilling"),

	/** South Africa - Rand */
	SOUTH_AFRICA("South Africa-Rand"),

	/** South Sudan - Sudanese Pound */
	SOUTH_SUDAN("South Sudan-Sudanese Pound"),

	/** Spain - Euro */
	SPAIN("Spain-Euro"),

	/** Sri Lanka - Rupee */
	SRI_LANKA("Sri Lanka-Rupee"),

	/** St. Lucia - East Caribbean Dollar */
	ST_LUCIA("St. Lucia-East Caribbean Dollar"),

	/** Sudan - Pound */
	SUDAN("Sudan-Pound"),

	/** Suriname - Dollar */
	SURINAME("Suriname-Dollar"),

	/** Sweden - Krona */
	SWEDEN("Sweden-Krona"),

	/** Switzerland - Franc */
	SWITZERLAND("Switzerland-Franc"),

	/** Syria - Pound */
	SYRIA("Syria-Pound"),

	/** Taiwan - Dollar */
	TAIWAN("Taiwan-Dollar"),

	/** Taiwan - Dollar */
	TAIWAN_DOLLAR("Taiwan-Dollar"),
	/** Tajikistan - Somoni */

	TAJIKISTAN("Tajikistan-Somoni"),
	/** Tanzania - Shilling */
	TANZANIA("Tanzania-Shilling"),

	/** Thailand - Baht */
	THAILAND("Thailand-Baht"),

	/** Timor-Leste - Dili */
	TIMOR_LESTE("Timor-Leste-Dili"),

	/** Togo - Cfa Franc */
	TOGO("Togo-Cfa Franc"),

	/** Tonga - Pa'Anga */
	TONGA("Tonga-Pa'Anga"),

	/** Trinidad & Tobago - Dollar */
	TRINIDAD_AND_TOBAGO("Trinidad & Tobago-Dollar"),

	/** Tunisia - Dinar */
	TUNISIA("Tunisia-Dinar"),

	/** Turkey - New Lira */
	TURKEY("Turkey-New Lira"),

	/** Turkmenistan - New Manat */
	TURKMENISTAN("Turkmenistan-New Manat"),

	/** Uganda - Shilling */
	UGANDA("Uganda-Shilling"),

	/** Ukraine - Hryvnia */
	UKRAINE("Ukraine-Hryvnia"),

	/** United Arab Emirates - Dirham */
	UNITED_ARAB_EMIRATES("United Arab Emirates-Dirham"),

	/** United Kingdom - Pound */
	UNITED_KINGDOM("United Kingdom-Pound"),

	/** Uruguay - Peso */
	URUGUAY("Uruguay-Peso"),

	/** Uzbekistan - Som */
	UZBEKISTAN("Uzbekistan-Som"),

	/** Vanuatu - Vatu */
	VANUATU("Vanuatu-Vatu"),

	/** Venezuela - Bolivar Soberano */
	VENEZUELA_BOLIVAR_SOBERANO("Venezuela-Bolivar Soberano"),

	/** Venezuela - Fuerte */
	VENEZUELA_FUERTE("Venezuela-Fuerte"),

	/** Vietnam - Dong */
	VIETNAM("Vietnam-Dong"),

	/** Western Samoa - Tala */
	WESTERN_SAMOA("Western Samoa-Tala"),

	/** Yemen - Rial */
	YEMEN("Yemen-Rial"),

	/** Zambia - New Kwacha */
	ZAMBIA("Zambia-New Kwacha"),

	/** Zimbabwe - Gold */
	ZIMBABWE_GOLD("Zimbabwe-Gold"),

	/** Zimbabwe - Rtgs */
	ZIMBABWE_RTGS("Zimbabwe-Rtgs");

	/** The string representation of the country-currency pair */
	private final String currency;

	/** Constructor to assign the currency string */
	Currencies(String currency) {
		this.currency = currency;
	}

	/** Returns the string representation of the currency */
	public String getCurrency() {
		return currency;
	}

	/** Map for reverse lookup by currency string */
	private static final Map<String, Currencies> BY_LABEL = new HashMap<>();

	static {
		for (Currencies currency : values()) {
			BY_LABEL.put(currency.currency, currency);
		}
	}

	/**
	 * Returns the enum constant corresponding to the given label.
	 * 
	 * @param label the currency string
	 * @return the corresponding Currencies enum, or null if not found
	 */
	@JsonCreator
	public static Currencies valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}