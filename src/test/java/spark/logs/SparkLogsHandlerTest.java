package spark.logs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SparkLogsHandlerTest {
    @Test
    public void testOptimizedLogicalPlanGetter() {
        SparkLogsHandler sparkLogsHandlerMock = mock(SparkLogsHandlerImp.class);

        doReturn(
                "Sort [revenue#396 DESC NULLS LAST], true\n" +
                        "+- Aggregate [n_name#49], [n_name#49, sum(value#390) AS revenue#396]\n" +
                        "   +- Project [n_name#49, if ((isnull(l_extendedprice#21) || isnull(l_discount#22))) null else UDF(l_extendedprice#21, l_discount#22) AS value#390]\n" +
                        "      +- Join Inner, ((o_custkey#105L = c_custkey#0L) && (s_nationkey#93L = c_nationkey#3L))\n" +
                        "         :- Project [n_name#49, l_extendedprice#21, l_discount#22, s_nationkey#93L, o_custkey#105L]\n" +
                        "         :  +- Join Inner, (l_orderkey#16L = o_orderkey#104L)\n" +
                        "         :     :- Project [n_name#49, l_extendedprice#21, l_discount#22, l_orderkey#16L, s_nationkey#93L]\n" +
                        "         :     :  +- Join Inner, (s_suppkey#90L = l_suppkey#18L)\n" +
                        "         :     :     :- Project [n_name#49, s_suppkey#90L, s_nationkey#93L]\n" +
                        "         :     :     :  +- Join Inner, (n_nationkey#48L = s_nationkey#93L)\n" +
                        "         :     :     :     :- Project [n_nationkey#48L, n_name#49]\n" +
                        "         :     :     :     :  +- Join Inner, (r_regionkey#56L = n_regionkey#50L)\n" +
                        "         :     :     :     :     :- Project [r_regionkey#56L]\n" +
                        "         :     :     :     :     :  +- Filter ((isnotnull(r_name#57) && (r_name#57 = ASIA)) && isnotnull(r_regionkey#56L))\n" +
                        "         :     :     :     :     :     +- Relation[r_regionkey#56L,r_name#57,r_comment#58] parquet\n" +
                        "         :     :     :     :     +- Project [n_nationkey#48L, n_name#49, n_regionkey#50L]\n" +
                        "         :     :     :     :        +- Filter (isnotnull(n_regionkey#50L) && isnotnull(n_nationkey#48L))\n" +
                        "         :     :     :     :           +- Relation[n_nationkey#48L,n_name#49,n_regionkey#50L,n_comment#51] parquet\n" +
                        "         :     :     :     +- Project [s_suppkey#90L, s_nationkey#93L]\n" +
                        "         :     :     :        +- Filter (isnotnull(s_nationkey#93L) && isnotnull(s_suppkey#90L))\n" +
                        "         :     :     :           +- Relation[s_suppkey#90L,s_name#91,s_address#92,s_nationkey#93L,s_phone#94,s_acctbal#95,s_comment#96] parquet\n" +
                        "         :     :     +- Project [l_orderkey#16L, l_suppkey#18L, l_extendedprice#21, l_discount#22]\n" +
                        "         :     :        +- Filter (isnotnull(l_suppkey#18L) && isnotnull(l_orderkey#16L))\n" +
                        "         :     :           +- Relation[l_orderkey#16L,l_partkey#17L,l_suppkey#18L,l_linenumber#19L,l_quantity#20,l_extendedprice#21,l_discount#22,l_tax#23,l_returnflag#24,l_linestatus#25,l_shipdate#26,l_commitdate#27,l_receiptdate#28,l_shipinstruct#29,l_shipmode#30,l_comment#31] parquet\n" +
                        "         :     +- Project [o_orderkey#104L, o_custkey#105L]\n" +
                        "         :        +- Filter ((((isnotnull(o_orderdate#108) && (o_orderdate#108 < 1995-01-01)) && (o_orderdate#108 >= 1994-01-01)) && isnotnull(o_orderkey#104L)) && isnotnull(o_custkey#105L))\n" +
                        "         :           +- Relation[o_orderkey#104L,o_custkey#105L,o_orderstatus#106,o_totalprice#107,o_orderdate#108,o_orderpriority#109,o_clerk#110,o_shippriority#111L,o_comment#112] parquet\n" +
                        "         +- Project [c_custkey#0L, c_nationkey#3L]\n" +
                        "            +- Filter (isnotnull(c_nationkey#3L) && isnotnull(c_custkey#0L))\n" +
                        "               +- Relation[c_custkey#0L,c_name#1,c_address#2,c_nationkey#3L,c_phone#4,c_acctbal#5,c_mktsegment#6,c_comment#7] parquet")
                .when(sparkLogsHandlerMock).getLatestAppOptimizedLogicalPlan();

        SparkLogsHandler sparkLogsHandler = new SparkLogsHandlerImp();
        assertEquals("Sort [revenue#396 DESC NULLS LAST], true\n" +
                "+- Aggregate [n_name#49], [n_name#49, sum(value#390) AS revenue#396]\n" +
                "   +- Project [n_name#49, if ((isnull(l_extendedprice#21) || isnull(l_discount#22))) null else UDF(l_extendedprice#21, l_discount#22) AS value#390]\n" +
                "      +- Join Inner, ((o_custkey#105L = c_custkey#0L) && (s_nationkey#93L = c_nationkey#3L))\n" +
                "         :- Project [n_name#49, l_extendedprice#21, l_discount#22, s_nationkey#93L, o_custkey#105L]\n" +
                "         :  +- Join Inner, (l_orderkey#16L = o_orderkey#104L)\n" +
                "         :     :- Project [n_name#49, l_extendedprice#21, l_discount#22, l_orderkey#16L, s_nationkey#93L]\n" +
                "         :     :  +- Join Inner, (s_suppkey#90L = l_suppkey#18L)\n" +
                "         :     :     :- Project [n_name#49, s_suppkey#90L, s_nationkey#93L]\n" +
                "         :     :     :  +- Join Inner, (n_nationkey#48L = s_nationkey#93L)\n" +
                "         :     :     :     :- Project [n_nationkey#48L, n_name#49]\n" +
                "         :     :     :     :  +- Join Inner, (r_regionkey#56L = n_regionkey#50L)\n" +
                "         :     :     :     :     :- Project [r_regionkey#56L]\n" +
                "         :     :     :     :     :  +- Filter ((isnotnull(r_name#57) && (r_name#57 = ASIA)) && isnotnull(r_regionkey#56L))\n" +
                "         :     :     :     :     :     +- Relation[r_regionkey#56L,r_name#57,r_comment#58] parquet\n" +
                "         :     :     :     :     +- Project [n_nationkey#48L, n_name#49, n_regionkey#50L]\n" +
                "         :     :     :     :        +- Filter (isnotnull(n_regionkey#50L) && isnotnull(n_nationkey#48L))\n" +
                "         :     :     :     :           +- Relation[n_nationkey#48L,n_name#49,n_regionkey#50L,n_comment#51] parquet\n" +
                "         :     :     :     +- Project [s_suppkey#90L, s_nationkey#93L]\n" +
                "         :     :     :        +- Filter (isnotnull(s_nationkey#93L) && isnotnull(s_suppkey#90L))\n" +
                "         :     :     :           +- Relation[s_suppkey#90L,s_name#91,s_address#92,s_nationkey#93L,s_phone#94,s_acctbal#95,s_comment#96] parquet\n" +
                "         :     :     +- Project [l_orderkey#16L, l_suppkey#18L, l_extendedprice#21, l_discount#22]\n" +
                "         :     :        +- Filter (isnotnull(l_suppkey#18L) && isnotnull(l_orderkey#16L))\n" +
                "         :     :           +- Relation[l_orderkey#16L,l_partkey#17L,l_suppkey#18L,l_linenumber#19L,l_quantity#20,l_extendedprice#21,l_discount#22,l_tax#23,l_returnflag#24,l_linestatus#25,l_shipdate#26,l_commitdate#27,l_receiptdate#28,l_shipinstruct#29,l_shipmode#30,l_comment#31] parquet\n" +
                "         :     +- Project [o_orderkey#104L, o_custkey#105L]\n" +
                "         :        +- Filter ((((isnotnull(o_orderdate#108) && (o_orderdate#108 < 1995-01-01)) && (o_orderdate#108 >= 1994-01-01)) && isnotnull(o_orderkey#104L)) && isnotnull(o_custkey#105L))\n" +
                "         :           +- Relation[o_orderkey#104L,o_custkey#105L,o_orderstatus#106,o_totalprice#107,o_orderdate#108,o_orderpriority#109,o_clerk#110,o_shippriority#111L,o_comment#112] parquet\n" +
                "         +- Project [c_custkey#0L, c_nationkey#3L]\n" +
                "            +- Filter (isnotnull(c_nationkey#3L) && isnotnull(c_custkey#0L))\n" +
                "               +- Relation[c_custkey#0L,c_name#1,c_address#2,c_nationkey#3L,c_phone#4,c_acctbal#5,c_mktsegment#6,c_comment#7] parquet",sparkLogsHandler.getLatestAppOptimizedLogicalPlan());

    }

    @Test
    public void testPhysicalPlanGetter() {
        SparkLogsHandlerImp sparkLogsHandlerMock = mock(SparkLogsHandlerImp.class);

        doReturn(
                "*(17) Sort [revenue#396 DESC NULLS LAST], true, 0\n" +
                        "+- Exchange rangepartitioning(revenue#396 DESC NULLS LAST, 8)\n" +
                        "   +- *(16) HashAggregate(keys=[n_name#49], functions=[sum(value#390)], output=[n_name#49, revenue#396])\n" +
                        "      +- Exchange hashpartitioning(n_name#49, 8)\n" +
                        "         +- *(15) HashAggregate(keys=[n_name#49], functions=[partial_sum(value#390)], output=[n_name#49, sum#401])\n" +
                        "            +- *(15) Project [n_name#49, if ((isnull(l_extendedprice#21) || isnull(l_discount#22))) null else UDF(l_extendedprice#21, l_discount#22) AS value#390]\n" +
                        "               +- *(15) SortMergeJoin [o_custkey#105L, s_nationkey#93L], [c_custkey#0L, c_nationkey#3L], Inner\n" +
                        "                  :- *(12) Sort [o_custkey#105L ASC NULLS FIRST, s_nationkey#93L ASC NULLS FIRST], false, 0\n" +
                        "                  :  +- Exchange hashpartitioning(o_custkey#105L, s_nationkey#93L, 8)\n" +
                        "                  :     +- *(11) Project [n_name#49, l_extendedprice#21, l_discount#22, s_nationkey#93L, o_custkey#105L]\n" +
                        "                  :        +- *(11) SortMergeJoin [l_orderkey#16L], [o_orderkey#104L], Inner\n" +
                        "                  :           :- *(8) Sort [l_orderkey#16L ASC NULLS FIRST], false, 0\n" +
                        "                  :           :  +- Exchange hashpartitioning(l_orderkey#16L, 8)\n" +
                        "                  :           :     +- *(7) Project [n_name#49, l_extendedprice#21, l_discount#22, l_orderkey#16L, s_nationkey#93L]\n" +
                        "                  :           :        +- *(7) SortMergeJoin [s_suppkey#90L], [l_suppkey#18L], Inner\n" +
                        "                  :           :           :- *(4) Sort [s_suppkey#90L ASC NULLS FIRST], false, 0\n" +
                        "                  :           :           :  +- Exchange hashpartitioning(s_suppkey#90L, 8)\n" +
                        "                  :           :           :     +- *(3) Project [n_name#49, s_suppkey#90L, s_nationkey#93L]\n" +
                        "                  :           :           :        +- *(3) BroadcastHashJoin [n_nationkey#48L], [s_nationkey#93L], Inner, BuildLeft\n" +
                        "                  :           :           :           :- BroadcastExchange HashedRelationBroadcastMode(List(input[0, bigint, true]))\n" +
                        "                  :           :           :           :  +- *(2) Project [n_nationkey#48L, n_name#49]\n" +
                        "                  :           :           :           :     +- *(2) BroadcastHashJoin [r_regionkey#56L], [n_regionkey#50L], Inner, BuildLeft\n" +
                        "                  :           :           :           :        :- BroadcastExchange HashedRelationBroadcastMode(List(input[0, bigint, true]))\n" +
                        "                  :           :           :           :        :  +- *(1) Project [r_regionkey#56L]\n" +
                        "                  :           :           :           :        :     +- *(1) Filter ((isnotnull(r_name#57) && (r_name#57 = ASIA)) && isnotnull(r_regionkey#56L))\n" +
                        "                  :           :           :           :        :        +- *(1) FileScan parquet [r_regionkey#56L,r_name#57] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/region], PartitionFilters: [], PushedFilters: [IsNotNull(r_name), EqualTo(r_name,ASIA), IsNotNull(r_regionkey)], ReadSchema: struct<r_regionkey:bigint,r_name:string>\n" +
                        "                  :           :           :           :        +- *(2) Project [n_nationkey#48L, n_name#49, n_regionkey#50L]\n" +
                        "                  :           :           :           :           +- *(2) Filter (isnotnull(n_regionkey#50L) && isnotnull(n_nationkey#48L))\n" +
                        "                  :           :           :           :              +- *(2) FileScan parquet [n_nationkey#48L,n_name#49,n_regionkey#50L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/nation], PartitionFilters: [], PushedFilters: [IsNotNull(n_regionkey), IsNotNull(n_nationkey)], ReadSchema: struct<n_nationkey:bigint,n_name:string,n_regionkey:bigint>\n" +
                        "                  :           :           :           +- *(3) Project [s_suppkey#90L, s_nationkey#93L]\n" +
                        "                  :           :           :              +- *(3) Filter (isnotnull(s_nationkey#93L) && isnotnull(s_suppkey#90L))\n" +
                        "                  :           :           :                 +- *(3) FileScan parquet [s_suppkey#90L,s_nationkey#93L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/supplier], PartitionFilters: [], PushedFilters: [IsNotNull(s_nationkey), IsNotNull(s_suppkey)], ReadSchema: struct<s_suppkey:bigint,s_nationkey:bigint>\n" +
                        "                  :           :           +- *(6) Sort [l_suppkey#18L ASC NULLS FIRST], false, 0\n" +
                        "                  :           :              +- Exchange hashpartitioning(l_suppkey#18L, 8)\n" +
                        "                  :           :                 +- *(5) Project [l_orderkey#16L, l_suppkey#18L, l_extendedprice#21, l_discount#22]\n" +
                        "                  :           :                    +- *(5) Filter (isnotnull(l_suppkey#18L) && isnotnull(l_orderkey#16L))\n" +
                        "                  :           :                       +- *(5) FileScan parquet [l_orderkey#16L,l_suppkey#18L,l_extendedprice#21,l_discount#22] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/lineitem], PartitionFilters: [], PushedFilters: [IsNotNull(l_suppkey), IsNotNull(l_orderkey)], ReadSchema: struct<l_orderkey:bigint,l_suppkey:bigint,l_extendedprice:double,l_discount:double>\n" +
                        "                  :           +- *(10) Sort [o_orderkey#104L ASC NULLS FIRST], false, 0\n" +
                        "                  :              +- Exchange hashpartitioning(o_orderkey#104L, 8)\n" +
                        "                  :                 +- *(9) Project [o_orderkey#104L, o_custkey#105L]\n" +
                        "                  :                    +- *(9) Filter ((((isnotnull(o_orderdate#108) && (o_orderdate#108 < 1995-01-01)) && (o_orderdate#108 >= 1994-01-01)) && isnotnull(o_orderkey#104L)) && isnotnull(o_custkey#105L))\n" +
                        "                  :                       +- *(9) FileScan parquet [o_orderkey#104L,o_custkey#105L,o_orderdate#108] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/orders], PartitionFilters: [], PushedFilters: [IsNotNull(o_orderdate), LessThan(o_orderdate,1995-01-01), GreaterThanOrEqual(o_orderdate,1994-01..., ReadSchema: struct<o_orderkey:bigint,o_custkey:bigint,o_orderdate:string>\n" +
                        "                  +- *(14) Sort [c_custkey#0L ASC NULLS FIRST, c_nationkey#3L ASC NULLS FIRST], false, 0\n" +
                        "                     +- Exchange hashpartitioning(c_custkey#0L, c_nationkey#3L, 8)\n" +
                        "                        +- *(13) Project [c_custkey#0L, c_nationkey#3L]\n" +
                        "                           +- *(13) Filter (isnotnull(c_nationkey#3L) && isnotnull(c_custkey#0L))\n" +
                        "                              +- *(13) FileScan parquet [c_custkey#0L,c_nationkey#3L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/customer], PartitionFilters: [], PushedFilters: [IsNotNull(c_nationkey), IsNotNull(c_custkey)], ReadSchema: struct<c_custkey:bigint,c_nationkey:bigint>")
                .when(sparkLogsHandlerMock).getLatestAppPhysicalPlan();

        SparkLogsHandler sparkLogsHandler = new SparkLogsHandlerImp();

        assertEquals("*(17) Sort [revenue#396 DESC NULLS LAST], true, 0\n" +
                "+- Exchange rangepartitioning(revenue#396 DESC NULLS LAST, 8)\n" +
                "   +- *(16) HashAggregate(keys=[n_name#49], functions=[sum(value#390)], output=[n_name#49, revenue#396])\n" +
                "      +- Exchange hashpartitioning(n_name#49, 8)\n" +
                "         +- *(15) HashAggregate(keys=[n_name#49], functions=[partial_sum(value#390)], output=[n_name#49, sum#401])\n" +
                "            +- *(15) Project [n_name#49, if ((isnull(l_extendedprice#21) || isnull(l_discount#22))) null else UDF(l_extendedprice#21, l_discount#22) AS value#390]\n" +
                "               +- *(15) SortMergeJoin [o_custkey#105L, s_nationkey#93L], [c_custkey#0L, c_nationkey#3L], Inner\n" +
                "                  :- *(12) Sort [o_custkey#105L ASC NULLS FIRST, s_nationkey#93L ASC NULLS FIRST], false, 0\n" +
                "                  :  +- Exchange hashpartitioning(o_custkey#105L, s_nationkey#93L, 8)\n" +
                "                  :     +- *(11) Project [n_name#49, l_extendedprice#21, l_discount#22, s_nationkey#93L, o_custkey#105L]\n" +
                "                  :        +- *(11) SortMergeJoin [l_orderkey#16L], [o_orderkey#104L], Inner\n" +
                "                  :           :- *(8) Sort [l_orderkey#16L ASC NULLS FIRST], false, 0\n" +
                "                  :           :  +- Exchange hashpartitioning(l_orderkey#16L, 8)\n" +
                "                  :           :     +- *(7) Project [n_name#49, l_extendedprice#21, l_discount#22, l_orderkey#16L, s_nationkey#93L]\n" +
                "                  :           :        +- *(7) SortMergeJoin [s_suppkey#90L], [l_suppkey#18L], Inner\n" +
                "                  :           :           :- *(4) Sort [s_suppkey#90L ASC NULLS FIRST], false, 0\n" +
                "                  :           :           :  +- Exchange hashpartitioning(s_suppkey#90L, 8)\n" +
                "                  :           :           :     +- *(3) Project [n_name#49, s_suppkey#90L, s_nationkey#93L]\n" +
                "                  :           :           :        +- *(3) BroadcastHashJoin [n_nationkey#48L], [s_nationkey#93L], Inner, BuildLeft\n" +
                "                  :           :           :           :- BroadcastExchange HashedRelationBroadcastMode(List(input[0, bigint, true]))\n" +
                "                  :           :           :           :  +- *(2) Project [n_nationkey#48L, n_name#49]\n" +
                "                  :           :           :           :     +- *(2) BroadcastHashJoin [r_regionkey#56L], [n_regionkey#50L], Inner, BuildLeft\n" +
                "                  :           :           :           :        :- BroadcastExchange HashedRelationBroadcastMode(List(input[0, bigint, true]))\n" +
                "                  :           :           :           :        :  +- *(1) Project [r_regionkey#56L]\n" +
                "                  :           :           :           :        :     +- *(1) Filter ((isnotnull(r_name#57) && (r_name#57 = ASIA)) && isnotnull(r_regionkey#56L))\n" +
                "                  :           :           :           :        :        +- *(1) FileScan parquet [r_regionkey#56L,r_name#57] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/region], PartitionFilters: [], PushedFilters: [IsNotNull(r_name), EqualTo(r_name,ASIA), IsNotNull(r_regionkey)], ReadSchema: struct<r_regionkey:bigint,r_name:string>\n" +
                "                  :           :           :           :        +- *(2) Project [n_nationkey#48L, n_name#49, n_regionkey#50L]\n" +
                "                  :           :           :           :           +- *(2) Filter (isnotnull(n_regionkey#50L) && isnotnull(n_nationkey#48L))\n" +
                "                  :           :           :           :              +- *(2) FileScan parquet [n_nationkey#48L,n_name#49,n_regionkey#50L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/nation], PartitionFilters: [], PushedFilters: [IsNotNull(n_regionkey), IsNotNull(n_nationkey)], ReadSchema: struct<n_nationkey:bigint,n_name:string,n_regionkey:bigint>\n" +
                "                  :           :           :           +- *(3) Project [s_suppkey#90L, s_nationkey#93L]\n" +
                "                  :           :           :              +- *(3) Filter (isnotnull(s_nationkey#93L) && isnotnull(s_suppkey#90L))\n" +
                "                  :           :           :                 +- *(3) FileScan parquet [s_suppkey#90L,s_nationkey#93L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/supplier], PartitionFilters: [], PushedFilters: [IsNotNull(s_nationkey), IsNotNull(s_suppkey)], ReadSchema: struct<s_suppkey:bigint,s_nationkey:bigint>\n" +
                "                  :           :           +- *(6) Sort [l_suppkey#18L ASC NULLS FIRST], false, 0\n" +
                "                  :           :              +- Exchange hashpartitioning(l_suppkey#18L, 8)\n" +
                "                  :           :                 +- *(5) Project [l_orderkey#16L, l_suppkey#18L, l_extendedprice#21, l_discount#22]\n" +
                "                  :           :                    +- *(5) Filter (isnotnull(l_suppkey#18L) && isnotnull(l_orderkey#16L))\n" +
                "                  :           :                       +- *(5) FileScan parquet [l_orderkey#16L,l_suppkey#18L,l_extendedprice#21,l_discount#22] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/lineitem], PartitionFilters: [], PushedFilters: [IsNotNull(l_suppkey), IsNotNull(l_orderkey)], ReadSchema: struct<l_orderkey:bigint,l_suppkey:bigint,l_extendedprice:double,l_discount:double>\n" +
                "                  :           +- *(10) Sort [o_orderkey#104L ASC NULLS FIRST], false, 0\n" +
                "                  :              +- Exchange hashpartitioning(o_orderkey#104L, 8)\n" +
                "                  :                 +- *(9) Project [o_orderkey#104L, o_custkey#105L]\n" +
                "                  :                    +- *(9) Filter ((((isnotnull(o_orderdate#108) && (o_orderdate#108 < 1995-01-01)) && (o_orderdate#108 >= 1994-01-01)) && isnotnull(o_orderkey#104L)) && isnotnull(o_custkey#105L))\n" +
                "                  :                       +- *(9) FileScan parquet [o_orderkey#104L,o_custkey#105L,o_orderdate#108] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/orders], PartitionFilters: [], PushedFilters: [IsNotNull(o_orderdate), LessThan(o_orderdate,1995-01-01), GreaterThanOrEqual(o_orderdate,1994-01..., ReadSchema: struct<o_orderkey:bigint,o_custkey:bigint,o_orderdate:string>\n" +
                "                  +- *(14) Sort [c_custkey#0L ASC NULLS FIRST, c_nationkey#3L ASC NULLS FIRST], false, 0\n" +
                "                     +- Exchange hashpartitioning(c_custkey#0L, c_nationkey#3L, 8)\n" +
                "                        +- *(13) Project [c_custkey#0L, c_nationkey#3L]\n" +
                "                           +- *(13) Filter (isnotnull(c_nationkey#3L) && isnotnull(c_custkey#0L))\n" +
                "                              +- *(13) FileScan parquet [c_custkey#0L,c_nationkey#3L] Batched: true, Format: Parquet, Location: InMemoryFileIndex[file:/media/kamal/New Volume/TPC_H/tpch-spark-master/parquet-files/customer], PartitionFilters: [], PushedFilters: [IsNotNull(c_nationkey), IsNotNull(c_custkey)], ReadSchema: struct<c_custkey:bigint,c_nationkey:bigint>", sparkLogsHandler.getLatestAppPhysicalPlan());
    }
}
