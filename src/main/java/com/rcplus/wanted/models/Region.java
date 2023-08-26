package com.rcplus.wanted.models;

import lombok.Getter;

@Getter
public enum Region {

    ETC(Nation.ETC, "기타"),

    SEOUL(Nation.KOREA, "서울"),
    BUSAN(Nation.KOREA, "부산"),
    DAEGU(Nation.KOREA, "대구"),
    INCHEON(Nation.KOREA, "인천"),
    GWANGJU(Nation.KOREA, "광주"),
    DAEJEON(Nation.KOREA, "대전"),
    ULSAN(Nation.KOREA, "울산"),
    SEJONG(Nation.KOREA, "세종"),
    GYEONGGI(Nation.KOREA, "경기"),
    GANGWON(Nation.KOREA, "강원"),
    CHUNGBOOK(Nation.KOREA, "충북"),
    CHUNGNAM(Nation.KOREA, "충남"),
    JEONBOOK(Nation.KOREA, "전북"),
    JEONNAM(Nation.KOREA, "전남"),
    GYEONGBOOK(Nation.KOREA, "경북"),
    GYEONGNAM(Nation.KOREA, "경남"),
    JEJU(Nation.KOREA, "제주"),

    TAIPEI_CITY(Nation.TAIWAN, "Taipei City"),
    NEW_TAIPEI_CITY(Nation.TAIWAN, "New Taipei City"),
    TAOYUAN_CITY(Nation.TAIWAN, "Taoyuan City"),
    TAICHUNG_CITY(Nation.TAIWAN, "Taichung City"),
    KAOHSIUNG_CITY(Nation.TAIWAN, "Kaohsiung City"),
    KEELUNG_CITY(Nation.TAIWAN, "Keelung City"),
    HSIN_CHU_CITY(Nation.TAIWAN, "Hsin-chu City"),
    HSIN_CHU_COUNTY(Nation.TAIWAN, "Hsin-chu County"),
    MIAOLI_COUNTY(Nation.TAIWAN, "Miaoli County"),
    CHAIYI_CITY(Nation.TAIWAN, "Chaiyi City"),
    CHANGHUA_COUNTY(Nation.TAIWAN, "Changhua County"),
    NANTOU_COUNTY(Nation.TAIWAN, "Nantou County"),
    YUNLIN_COUNTY(Nation.TAIWAN, "Yunlin County"),
    CHAIYI_COUNTY(Nation.TAIWAN, "Chaiyi County"),
    PINGTUNG_COUNTY(Nation.TAIWAN, "Pingtung County"),
    YALIN_COUNTY(Nation.TAIWAN, "Yalin County"),
    HUALIEAN_COUNTY(Nation.TAIWAN, "Hualien County"),
    TAITUNG_COUNTY(Nation.TAIWAN, "Taitung County"),
    WUHU_COUNTY(Nation.TAIWAN, "Wuhu County"),
    JINMEN_COUNTY(Nation.TAIWAN, "Jinmen County"),
    MATSU(Nation.TAIWAN, "Matsu"),

    SINGAPORE_ALL(Nation.SINGAPORE, "All"),

    TOKYO(Nation.JAPAN, "Tokyo"),
    KANAGAWA(Nation.JAPAN, "Kanagawa"),
    CHIBA(Nation.JAPAN, "Chiba"),
    AICHI(Nation.JAPAN, "Aichi"),
    OSAKA(Nation.JAPAN, "Osaka"),
    HYOGO(Nation.JAPAN, "Hyogo"),
    KYOTO(Nation.JAPAN, "Kyoto"),
    FUKUOKA(Nation.JAPAN, "Fukuoka"),
    OKINAWA(Nation.JAPAN, "Okinawa"),
    HOKKAIDO(Nation.JAPAN, "Hokkaido"),
    SAITAMA(Nation.JAPAN, "Saitama"),
    HIROSHIMA(Nation.JAPAN, "Hiroshima"),
    MIYAGI(Nation.JAPAN, "Miyagi"),
    JAPAN_ETC(Nation.JAPAN, "Etc");

    private Nation nation;
    private String name;

    Region(Nation nation, String name) {
        this.nation = nation;
        this.name = name;
    }
}
