// Guia_0701
// João Madeira Carneiro Braga de Freitas - 800854
module Guia_0701 (
    input a,
    input b,
    input select,
    output out1,
    output out2
);

wire nand_result;
wire and_result;

nand NAND1 (nand_result, a, b);
and AND1 (and_result, a, b);

assign out1 = (select) ? and_result : nand_result;
assign out2 = (select) ? nand_result : and_result;

endmodule

module test_Guia_0701;

reg a;
reg b;
reg select;
wire out1;
wire out2;

Guia_0701 LU (
    .a(a),
    .b(b),
    .select(select),
    .out1(out1),
    .out2(out2)
);

initial begin
    $display("Guia_0701 - Teste da Unidade Lógica");
    $display("a b select out1 out2");
    
    

    a = 0;
    b = 0;
    select = 0;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
     #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 0;
    b = 1;
    select = 0;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 1;
    b = 0;
    select = 0;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 1;
    b = 1;
    select = 0;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 0;
    b = 0;
    select = 1;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 0;
    b = 1;
    select = 1;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 1;
    b = 0;
    select = 1;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    a = 1;
    b = 1;
    select = 1;
    #1 $monitor("%b %b %b      %b    %b", a, b, select, out1, out2);
    
    $finish;
end

endmodule
