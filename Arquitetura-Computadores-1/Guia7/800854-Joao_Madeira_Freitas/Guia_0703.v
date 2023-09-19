// Guia_0703
// João Madeira Carneiro Braga de Freitas - 800854
module Guia_0703 (
    input a,
    input b,
    input select_group,
    input select_operation,
    output out
);

wire and_result, nand_result, or_result, nor_result;

and AND1 (and_result, a, b);
nand NAND1 (nand_result, a, b);
or OR1 (or_result, a, b);
nor NOR1 (nor_result, a, b);

assign out = (select_group) ? 
              (select_operation) ? nor_result : or_result :
              (select_operation) ? nand_result : and_result;

endmodule

module test_Guia_0703;

reg a;
reg b;
reg select_group;
reg select_operation;
wire out;

Guia_0703 LU (
    .a(a),
    .b(b),
    .select_group(select_group),
    .select_operation(select_operation),
    .out(out)
);

initial begin
    $display("Guia_0703 - Teste da Unidade Lógica");
    $display("a b select_group select_operation out");
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 0;
    select_group = 0;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 1;
    select_group = 0;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 0;
    select_group = 0;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 1;
    select_group = 0;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 0;
    select_group = 0;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 1;
    select_group = 0;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 0;
    select_group = 0;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 1;
    select_group = 0;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 0;
    select_group = 1;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 1;
    select_group = 1;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 0;
    select_group = 1;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 1;
    select_group = 1;
    select_operation = 0;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 0;
    select_group = 1;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 0;
    b = 1;
    select_group = 1;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 0;
    select_group = 1;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    a = 1;
    b = 1;
    select_group = 1;
    select_operation = 1;
    #1 $monitor("%b %b %b            %b                %b", a, b, select_group, select_operation, out);
    
    $finish;
end

endmodule
