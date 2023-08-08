/*
 Guia_0104.v
 800854 - João Madeira Carneiro Braga de Freitas
*/

module Guia_0104;
    // define data
    integer x = 21;
    reg [7:0] b = 0; // binary
    // actions
    initial
    begin : main
    $display ( "Guia_0104" );
    b = x;
    $display ( "a(2) = %8b", b );
    $display ( "a(4) = [%2b] [%2b] [%2b] [%2b] = %x%x%x%x", b[7:6], b[5:4], b[3:2], b[1:0], b[7:6], b[5:4], b[3:2], b[1:0] );
    x = 26;
    b = x;
    $display ( "b(2) = %8b", b );
    $display ( "b(8) = [%2b] [%3b] [%3b] = %x%x%x", b[7:6], b[5:3], b[2:0],b[7:6], b[5:3], b[2:0] );
    x = 39;
    b = x;
    $display ( "c(2) = %8b", b );
    $display ( "c(16) = [%4b] [%4b] = %x%x", b[7:4], b[3:0], b[7:4], b[3:0]  );
    x = 41;
    b = x;
    $display ( "d(2) = %8b", b );
    $display ( "d(8) = [%2b] [%3b] [%3b] = %x%x%x", b[7:6], b[5:3], b[2:0],b[7:6], b[5:3], b[2:0] );
    x = 45;
    b = x;
    $display ( "e(2) = %8b", b );
    $display ( "e(4) = [%2b] [%2b] [%2b] [%2b] = %x%x%x%x", b[7:6], b[5:4], b[3:2], b[1:0], b[7:6], b[5:4], b[3:2], b[1:0] );



    end // main
endmodule // Guia_0104