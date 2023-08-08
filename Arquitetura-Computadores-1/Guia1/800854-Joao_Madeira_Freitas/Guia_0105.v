/*
 Guia_0105.v
 800854 - João Madeira Carneiro Braga de Freitas
*/

module Guia_0105;
    // define data
    integer x = 13; // decimal
    reg [0:8][7:0] a = "PUC-Minas"; 
    reg [0:6][7:0] b = "2023-02";
    reg [0:13][7:0] c = "Belo Horizonte";
    reg [0:4][7:0] d;
    reg [0:4][7:0] e;
     
    
    // actions
    initial
    begin : main
    $display ( "Guia_0105" );
    $display ( "a = %s" , a );
    $display ( "a(16_ASCII) = %h" , a );
    $display ( "b = %s" , b );
    $display ( "b(16_ASCII) = %h" , b );
    $display ( "c = %s" , c );
    $display ( "c(2_ASCII) = %b" , c );
    d[0] = 8'o116;
    d[1] = 8'o157;
    d[2] = 8'o151;
    d[3] = 8'o164;
    d[4] = 8'o145;
    $display ("d(8) = 116 157 151 164 145");
    $display ("d(ASCII) = %s", d);
    e[0] = 8'h4d;
    e[1] = 8'h2e;
    e[2] = 8'h20;
    e[3] = 8'h47;
    e[4] = 8'h2e;
    $display ("e(16) = 4D 2E 20 47 2E");
    $display ("e(ASCII) = %s", e);

    
    end // main
endmodule // Guia_0105