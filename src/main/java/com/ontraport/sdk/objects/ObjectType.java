package com.ontraport.sdk.objects;

import java.util.HashMap;
import java.util.Map;

public enum ObjectType {
    CONTACT(0),
    TASK(1),
    USER(2),
    STAFF(2),
    GROUP(3),
    LOG(4),
    SEQUENCE(5),
    RULE(6),
    MESSAGE(7),
    SEQUENCE_SUBSCRIBER(8),
    NOTE(12),
    BLAST(13),
    TAG(14),
    PRODUCT(16),
    PURCHASE(17),
    FULFILLMENT(19),
    LANDINGPAGE(20),
    LEGACY_FORM(22),
    QUEUED_MESSAGE(23),
    PENDING_MAIL(27),
    PURCHASE_LOG(30),
    PARTNER_PROGRAM(35),
    PARTNER(36),
    REFERRAL(37),
    COMMISSION(38),
    PARTNER_PROMOTIONAL(40),
    UPSELL_FORM(42),
    WORDPRESS_MEMBERSHIP(43),
    OPEN_ORDER(44),
    CREDIT_CARD(45),
    TRANSACTION(46),
    SHIPPED_PACKAGE(47),
    SHIPPING_FULFILLMENT(49),
    ORDER(52),
    FACEBOOK(53),
    CUSTOM_DOMAIN(58),
    ROLE(61),
    TAX(63),
    SHIPPING_METHOD(64),
    OFFER(65),
    TASK_OUTCOME(66),
    WORDPRESS(67),
    MESSAGE_TEMPLATE(68),
    LEAD_ROUTER(69),
    GATEWAY(70),
    MARKETING_CAMPAIGN(75),
    MARKETING_LEAD_SOURCE(76),
    MARKETING_MEDIUM(77),
    MARKETING_CONTENT(78),
    MARKETING_TERM(79),
    TRACKING_CAMPAIGN(75),
    TRACKING_LEAD_SOURCE(76),
    TRACKING_MEDIUM(77),
    TRACKING_CONTENT(78),
    TRACKING_TERM(79),
    TRACKED_LINK(80),
    PARTNER_PROGRAM_PRODUCT(87),
    URL_HISTORY(88),
    TASK_NOTE(89),
    TASK_HISTORY(90),
    SUBSCRIBER_RETENTION_REPORT(92),
    SUBSCRIPTION_SALES_REPORT(93),
    SALES_REPORT(94),
    PRODUCT_SALES_LOG_REPORT(95),
    CUSTOMER_VALUE_REPORT(96),
    SHIPPING_COLLECTED_REPORT(97),
    TAX_REPORT(98),
    CUSTOM_OBJECT(99),
    AUTOMATION_LOG(100),
    CUSTOM_OBJECT_RELATIONSHIP(102),
    FORM(122),
    COUPON(123),
    COUPON_CODE(124),
    COUPON_PRODUCTS(125),
    TAG_SUBSCRIBER(138),
    CAMPAIGN_BUILDER(140),
    CAMPAIGN(140),
    DELETED_ORDER(146),
    DEAL(149),
    COMPANY(150);

    private final int _id;
    private static Map<Integer, ObjectType> map = new HashMap<>();

    ObjectType(final int id) {
        _id = id;
    }

    static {
        for (ObjectType o : ObjectType.values()) {
            map.put(o.getId(), o);
        }
    }

    public int getId() {
        return _id;
    }

    public static ObjectType valueOf(int id) {
        return map.get(id);
    }
}
