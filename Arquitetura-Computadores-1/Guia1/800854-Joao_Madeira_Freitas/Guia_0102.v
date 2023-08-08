/*
 Guia_0102.v
 800854 - João Madeira Carneiro Braga de Freitas
*/
module Guia_0102;
// define data
 integer x = 0; // decimal

 reg [7:0] a = 8'b0010110; // binary (bits - little endian)
 reg [7:0] b = 8'b0011011; // binary (bits - little endian)
 reg [7:0] c = 8'b0100100; // binary (bits - little endian)
 reg [7:0] d = 8'b0101001; // binary (bits - little endian)
 reg [7:0] e = 8'b0110111; // binary (bits - little endian)

// actions
 initial
 begin : main
   $display("Guia_0102");

   // Mostrar e converter variavel a
   $display("a(2) = %8b", a);
   x = a;
   $display("a(10) = %d", x);

   // Mostrar e converter variavel b
   $display("b(2) = %8b", b);
   x = b;
   $display("b(10) = %d", x);

   // Mostrar e converter variavel c
   $display("c(2) = %8b", c);
   x = c;
   $display("c(10) = %d", x);

   // Mostrar e converter variavel d
   $display("d(2) = %8b", d);
   x = d;
   $display("d(10) = %d", x);

   // Mostrar e converter variavel e
   $display("e(2) = %8b", e);
   x = e;
   $display("e(10) = %d", x);
 end // main
endmodule // Guia_0102