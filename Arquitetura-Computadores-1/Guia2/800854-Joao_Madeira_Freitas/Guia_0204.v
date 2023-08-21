/*
Guia_0204.v
João Madeira Carneiro Braga de Freitas
800854
*/

module Guia_0204;
// define data
integer a = 312;
reg [11:0] b = 12'h3e7;      
reg [9:0] c = 10'o751;
reg [11:0] d = 12'o7231;      
reg [15:0] e = 16'hab52;      
reg [15:0] bin = 16'b0;       


initial
begin : main
    bin = a;
    $display("Guia_0204");
    $display("a = 0.%d (4)", a);
    $display("a = 0.%b (2)", bin[9:0]);


    bin[11:0] = b;            
    $display("b = 0.%h (16)", b[11:0]);
    $display("b = 0.%b (2)", bin[11:0]);
    $display("b = 0.%d%d%d%d%d%d (4)", bin[11:10], bin[9:8], bin[7:6], bin[5:4], bin[3:2],bin[1:0]);

    bin[9:0] = c;
    $display("c = 0.%o (8)", c);
    $display("c = 0.%b (2)", bin[9:0]);

    bin[11:0] = d;           
    $display("d = 0.%o (8)", d[11:0]); 
    $display("d = 0.%b (2)", bin[11:0]); 
    $display("b = 0.%d%d%d%d%d (4)", bin[11:10], bin[9:8], bin[7:6], bin[5:4], bin[3:2]);
    bin[15:0] = e;
    $display("e = 0.%h (16)", e[15:0]);
    $display("e = 0.%b (2)", bin[15:0]);
    $display("e = 0.%d%d%d%d%d%d (4)", bin[11:10], bin[9:8], bin[7:6], bin[5:4], bin[3:2],bin[1:0]);


end
endmodule

