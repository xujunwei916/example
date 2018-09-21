 select t1.passid passid from (
	select count(distinct qq_modify) count1,count(distinct wechat_modify) count2,passid from cheat_api_data where dt >="2018-08-05" and dt <="2018-08-07" and qq_modify!=-1 and wechat_modify!=-1 group by passid having count1=1 and count2=1
) t1
join
(
select passid,count(1) count_dt from (select dt,passid,count(passid) count1 from cheat_api_data where dt >="2018-08-05" and dt <="2018-08-07" and qq_modify!=-1 and wechat_modify!=-1 group by dt,passid having count1>=2) group by passid having count_dt>=2) t2 on t1.passid = t2.passid