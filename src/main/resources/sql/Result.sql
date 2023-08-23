-- 获取全部产品和最长链路
select
     max(lev) as lage,  -- 最大层级
     father,            -- 产品
     t_date,            -- 年月
     fact               -- 工厂
from ods.dependency
group by father,t_date,fact

