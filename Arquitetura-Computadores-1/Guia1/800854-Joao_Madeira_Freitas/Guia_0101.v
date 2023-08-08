/*
 Guia_0101.v
 800854 - João Madeira Carneiro Braga de Freitas
*/
module Guia_0101;
  // define data
  integer d = 26; // decimal
  
  integer d3 = 723; // decimal
  integer d4 = 312; // decimal
  integer d5 = 365; // decimal
  reg [7:0] b = 0; // binary (bits - little endian)
  reg [15:0] b2 = 0;// binary 

  // actions
  initial
  begin : main
    $display("Guia_0101");
    $display("a(10) = %d", d);
    b = d;
    $display("a(2) = %8b", b);
    d = 53;
    $display("b(10) = %d", d);
    b = d;
    $display("b(2) = %8b", b);
    d = 723;
    $display("c(10) = %d", d);
    b2 = d;
    $display("c(2) = %8b", b2);
    d = 312;
    $display("d(10) = %d", d);
    b2 = d;
    $display("d(2) = %8b", b2);
    d = 365;
    $display("e(10) = %d", d);
    b2 = d;
    $display("e(2) = %8b", b2);
  end // main
endmodule // Guia_0101