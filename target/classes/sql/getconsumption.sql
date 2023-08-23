select
    "real_unit_expend"
from "ODS"."dwd_sh_prod_ord_prde_cse_007"
where "finpro_code" = ?
and "fact" = '5002'
and "prod_ord_nr" = ?
and "tranim_date" = '202306'
and "prod_ord_nr2" = ?
and "project" = ?
