create table securities_symbols
(
    id                int auto_increment primary key,
    name              varchar(50) not null,
    oldest_date_in_db bit         null,
    symbol            varchar(15) not null,
    type              varchar(10) not null
);

create table securities_ohlcv
(
    id             bigint auto_increment primary key,
    adjusted_close decimal(12, 2) null,
    close          decimal(12, 2) not null,
    date           datetime       null,
    high           decimal(12, 2) not null,
    low            decimal(12, 2) not null,
    open           decimal(12, 2) not null,
    volume         bigint         null,
    symbol_id      int            not null,
    constraint FK_securities_ohlcv_securities_symbols__id
        foreign key (symbol_id) references securities_symbols (id)
);

create table backtest_model
(
    id                 int auto_increment primary key,
    model_name         varchar(31)    not null,
    end_date           datetime       not null,
    initial_investment decimal(19, 2) null,
    model_status       varchar(255)   not null,
    start_date         datetime       not null,
    symbol_id          int            not null,
    constraint FK_backtest_model_securities_symbols__symbol_id
        foreign key (symbol_id) references securities_symbols (id)
);

create table backtest_rule
(
    rule_name varchar(31)  not null,
    id        int auto_increment primary key,
    rule_type varchar(255) not null
);

create table backtest_model_rule
(
    model_id int not null,
    rule_id  int not null,
    constraint FK5q39rok9k80a86mwyenipg5o4
        foreign key (rule_id) references backtest_rule (id),
    constraint FKjfgj8yuruxa6emmvf0c1pv302
        foreign key (model_id) references backtest_model (id)
);



create table backtest_rule_parameters
(
    id              int auto_increment primary key,
    parameter_name  varchar(255) null,
    parameter_value varchar(255) null,
    rule            int          null,
    constraint FK7qmcagsy8misdb095cly40knl
        foreign key (rule) references backtest_rule (id)
);

create table backtest_rule_result
(
    rule_name       varchar(31) not null,
    id              int auto_increment primary key,
    date            datetime    null,
    rule_result     bit         null,
    churn_day       bit         null,
    d_day           bit         null,
    ddays_in_window int         null,
    follow_thru_day bit         null,
    pivot_day       bit         null,
    rule_id         int         null,
    constraint FKip6idcpwg0ntqqreaclhfafdq
        foreign key (rule_id) references backtest_rule (id)
);

create table backtest_trade
(
    id         int auto_increment primary key,
    buy_date   datetime       null,
    buy_price  decimal(12, 2) not null,
    sell_date  datetime       null,
    sell_price decimal(12, 2) not null,
    model_id   int            not null,
    constraint FK4ttdgnfab2sdj5bgs3pi34fuk
        foreign key (model_id) references backtest_model (id)
);

create table ibd50_custom_index
(
    id               int auto_increment primary key,
    index_name       varchar(255) not null,
    rank_range_end   int          not null,
    rank_range_start int          not null
);


create table ibd50_stock_ohlcv
(
    id             int auto_increment primary key,
    adjusted_close decimal(12, 2) null,
    close          decimal(12, 2) null,
    date           datetime       null,
    high           decimal(12, 2) null,
    low            decimal(12, 2) null,
    open           decimal(12, 2) null,
    volume         bigint         null,
    symbol_id      int            not null,
    constraint FKdnwkapy0s34hhlowd66w95i3q
        foreign key (symbol_id) references securities_symbols (id)
);

create table ibd50_tracking
(
    id                int auto_increment primary key,
    creation_time     datetime       not null,
    modification_time datetime       not null,
    active            bit            null,
    join_date         datetime       null,
    join_price        decimal(19, 2) null,
    leave_price       decimal(19, 2) null,
    leave_date        datetime       null,
    percent_return    double         null,
    symbol_id         int            not null,
    constraint FKad3jabhm7hdlscv3smoxs5mtp
        foreign key (symbol_id) references securities_symbols (id)
);

create table ibd50_ranking
(
    id                                        int auto_increment primary key,
    acc_dis_rating                            varchar(255)   null,
    current_ranking                           bit            null,
    annual_profit_margin_latest_year          double         null,
    annual_roe_last_year                      double         null,
    composite_rating                          double         null,
    creation_time                             datetime       not null,
    current_price                             decimal(10, 2) not null,
    eps_estimated_percent_change_current_year double         null,
    eps_percent_change_current_qtr            double         null,
    eps_percent_change_last_qtr               double         null,
    eps_percent_change_prior_qtr              double         null,
    eps_rating                                double         null,
    group_relative_strength_rating            varchar(255)   null,
    management_own_percent                    double         null,
    modification_time                         datetime       not null,
    percent_off_high                          double         null,
    price_change                              decimal(10, 2) not null,
    price_percent_change                      double         null,
    qtrs_rising_sponsorship                   int            null,
    ranking                                   int            not null,
    rank_date                                 date           not null,
    rs_rating                                 double         null,
    sale_percent_change_last_qtr              double         null,
    smr_rating                                varchar(255)   null,
    volume                                    bigint         null,
    volume_percent_change                     double         null,
    symbol_id                                 int            not null,
    tracking_id                               int            not null,
    constraint FKc8oti5ohp5xqa1kyhqt4acegx
        foreign key (tracking_id) references ibd50_tracking (id),
    constraint FKgigvexeh8ev44vrstrn8fx4o6
        foreign key (symbol_id) references securities_symbols (id)
);

create table ibd50_index_shares
(
    id              int auto_increment primary key,
    share_count     double null,
    custom_index_id int    not null,
    ohlcv_id        int    not null,
    ranking_id      int    not null,
    constraint UK_6sep88wlmm03hm1gysnufha0h
        unique (ohlcv_id),
    constraint FK155pjyopq6ajrpog35i0x4ygy
        foreign key (ranking_id) references ibd50_ranking (id),
    constraint FKe7fcwb8g5al28lhlex20s9227
        foreign key (ohlcv_id) references ibd50_stock_ohlcv (id),
    constraint FKq77bkwefc2cmuv0i9awek5cvg
        foreign key (custom_index_id) references ibd50_custom_index (id)
);
