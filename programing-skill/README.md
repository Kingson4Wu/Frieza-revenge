+ 编程技巧---简单的if/else用条件表达式代替
```java
  // 组织持久化对象
            BrandPeriodSortEntity entity = new BrandPeriodSortEntity();
            entity.setBrand_id(period_id == null || "".equals(period_id) ? null : Integer.parseInt(period_id));
            entity.setBrand_name(period_name == null || "".equals(period_name) ? null : "%" + period_name + "%");
            entity.setWarehouse(period_warehouse == null || "".equals(period_warehouse) ? null : period_warehouse);
            entity.setSell_time_from(fromMilliSecond == null ? null : fromMilliSecond / 1000);
            entity.setSell_time_to(toMilliSecond == null ? null : toMilliSecond / 1000);
            String channel_id_plus = null;
            if ("".equals(channel_id)) {
                channel_id = null;
            }
            if (null != period_warehouses && period_warehouses.length > 0) {
                entity.setWarehouses(period_warehouses);
            }
```

