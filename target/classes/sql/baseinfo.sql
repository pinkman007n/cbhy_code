select
    *
from ods.dependency
where father = ?
and fact = ?
and prod_ord_nr = ?
and t_date = ?
and lev = 2
