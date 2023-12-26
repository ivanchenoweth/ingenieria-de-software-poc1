--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARPROVEEDOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARPROVEEDOR" (
v_idproveedor IN proveedor.id_proveedor%type
)

is
BEGIN

DELETE FROM PROVEEDOR WHERE id_proveedor = v_idproveedor;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el proveedor' || v_idproveedor || ' . Operacion interrumpida.');
rollback;
end;

/
