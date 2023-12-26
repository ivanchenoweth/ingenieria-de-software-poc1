--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARSERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARSERVICIO" (
v_idservicio IN servicio.id_servicio%type
)

is
BEGIN

DELETE FROM SERVICIO WHERE id_servicio = v_idservicio;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el servicio' || v_idservicio || ' . Operacion interrumpida.');
rollback;
end;

/
