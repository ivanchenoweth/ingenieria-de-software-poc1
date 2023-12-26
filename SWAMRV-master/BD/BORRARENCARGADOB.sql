--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARENCARGADOB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARENCARGADOB" (
v_rut IN encargado_bodega.rut_encargado%type
)

is
BEGIN
DELETE FROM ENCARGADO_BODEGA WHERE rut_encargado = v_rut;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el mecanico' || v_rut || ' . Operacion interrumpida.');
rollback;
end;

/
