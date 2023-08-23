select
   distinct
	father,
    prod_ord_nr,
    substr(dep,instr(dep,'-',1)+1) dep-- 依赖
from ods.dependency
where father = '000000000010000952' and fact = '5002' and prod_ord_nr = '000120000065' and lev = ?