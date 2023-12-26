--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BUSCARENCARGADOB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BUSCARENCARGADOB" (
v_rutencargado IN encargado_bodega.rut_encargado%type,
v_contrasena IN encargado_bodega.contrasena%type,
v_nombre OUT encargado_bodega.nombre%type,
v_apellidopaterno OUT encargado_bodega.apellido_paterno%type,
v_apellidomaterno OUT encargado_bodega.apellido_materno%type
)

is
BEGIN

SELECT nombre, apellido_paterno, apellido_materno
INTO v_nombre, v_apellidopaterno, v_apellidomaterno
FROM ENCARGADO_BODEGA
WHERE rut_encargado = v_rutencargado
and contrasena = v_contrasena;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se ha encontrado el Encargado de Bodega');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar cargar al usuario' || v_rutencargado || ' . Operacion interrumpida.');
rollback;
end;

/
