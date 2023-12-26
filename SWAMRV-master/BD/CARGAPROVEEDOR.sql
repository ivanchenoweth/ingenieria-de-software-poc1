--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAPROVEEDOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAPROVEEDOR" (
v_nombre IN proveedor.nombre%type,
v_idproveedor OUT proveedor.id_proveedor%type,
v_telefono OUT proveedor.telefono%type,
v_descripcion OUT proveedor.descripcion%type,
v_direccion OUT proveedor.direccion%type,
v_email OUT proveedor.email%type,
v_idcomuna OUT proveedor.id_comuna%type)

as
BEGIN
SELECT id_proveedor, telefono, descripcion, direccion, email, id_comuna
INTO v_idproveedor, v_telefono, v_descripcion, v_direccion, v_email, v_idcomuna
FROM PROVEEDOR WHERE NOMBRE = v_nombre;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('PROVEEDOR no encontrado en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el proveedor '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
