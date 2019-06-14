//package com.cloud.jpa.config.shardingjdbc;
//
//import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
//import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
//
//import java.util.Collection;
//
//public final class ModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
//
//    @Override
//    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> shardingValue) {
//        for (String each : tableNames) {
//            for (String table : JPAConfig.TABLES) {
//                if (each.equalsIgnoreCase(table + "_" + confDBRouteFromPersionCard(shardingValue.getValue()))) {
//                    return each;
//                }
//            }
//        }
//        return shardingValue.getLogicTableName();
//    }
//
//    private int confDBRouteFromPersionCard(String persionCard) {
//        if (null == persionCard || persionCard.length() < 15) {
//            throw new IllegalArgumentException("身份证无效");
//        }
//        String lastChar = persionCard.substring(persionCard.length() - 1);
//        return "x".equalsIgnoreCase(lastChar) ? 10 : Integer.valueOf(lastChar);
//    }
//
//    public static void main(String[] args) {
//        String persionCard = "430703199304309639";
//        if (null == persionCard || persionCard.length() < 15){
//            throw new IllegalArgumentException("身份证无效");
//        }
//        String lastChar = persionCard.substring(persionCard.length()-1);
//        System.out.println(lastChar);
//    }
//}
