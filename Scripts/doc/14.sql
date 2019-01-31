-- PL/SQL
-- block, conditional branching, loops

-- output to console
set serveroutput on

-- a minimal pl/sql block
begin
    dbms_output.put_line('Hello PL/SQL');
end;
/

-- declaring variables
declare
    v_width integer;
    v_height integer := 2;
    v_area integer := 6;
begin
    v_width := v_area / v_height;
    dbms_output.put_line('v_width = ' || v_width);
end;
/

-- EXCEPTION
declare
    v_width integer;
    v_height integer := 0;
    v_area integer := 6;
begin
    v_width := v_area / v_height;
    dbms_output.put_line('v_width = ' || v_width);
exception
    when zero_divide then
        dbms_output.put_line('Division by zero');
end;
/

-- OTHERS EXCEPTION
BEGIN
    DBMS_OUTPUT.PUT_LINE(1 / 0);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An exception occurred');
END;
/

-- Conditional Logic
declare
    v_a integer := 1;
    v_b integer := 2;
begin
    if v_a > 0 then
        dbms_output.put_line('v_a is ' || v_a);
        if v_b > 0 then
            dbms_output.put_line('Both positive!');
        end if;
    elsif v_a = 0 then
        dbms_output.put_line('v_a is zero');
    else
        dbms_output.put_line('v_a is negative');
    end if;
end;
/

-- LOOP
declare
    v_x integer;
begin
    -- LOOP - EXIT WHEN
    v_x := 0;
    loop
        v_x := v_x + 1;
        exit when v_x = 5;
    end loop;
    dbms_output.put_line('loop completed: ' || v_x);

    -- CONTINUE
    v_x := 0;
    loop
        v_x := v_x + 1;
        if v_x = 3 then
            dbms_output.put_line('something special on three');
            continue;
        end if;
        exit when v_x = 5;
    end loop;
    dbms_output.put_line('loop 2 completed: ' || v_x);

    -- CONTINUE WHEN
    v_x := 0;
    loop
        v_x := v_x + 1;
        continue when v_x = 3;

        dbms_output.put_line('something when v_x is not three: ' || v_x);
        exit when v_x = 5;
    end loop;
    dbms_output.put_line('loop 3 completed: ' || v_x);

    -- WHILE LOOP
    v_x := 0;
    while v_x < 5 loop
        v_x := v_x + 1;
    end loop;
    dbms_output.put_line('while loop completed: ' || v_x);

    -- FOR LOOP
    for i in 1..5 loop
        dbms_output.put_line('for loop: ' || i);
    end loop;
    
    -- REVERSE FOR LOOP
    for i in reverse 1..5 loop
        dbms_output.put_line('for loop: ' || i);
    end loop;
end;
/
