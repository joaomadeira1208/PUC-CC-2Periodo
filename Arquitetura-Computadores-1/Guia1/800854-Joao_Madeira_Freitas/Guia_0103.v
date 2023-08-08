/*
 Guia_0103.v
 800854 - João Madeira Carneiro Braga de Freitas
*/
module Guia_0103;
// define data
integer x = 61; // decimal
reg [7:0] b = 0; // binary
integer x2 = 54;
integer x3 = 77;
integer x4 = 151;
integer x5 = 738;

// actions
initial
begin : main
  $display("Guia_0103");
  $display("a = %d", x);
  b = x;
  $display("a = [%2b] [%2b] [%2b] [%2b] = %o%o%o%o base 4", b[7:6], b[5:4], b[3:2], b[1:0], b[7:6], b[5:4], b[3:2], b[1:0]);
  $display("b = %d", x2);
  b = x2;
  $display("b = %o (8)", b);
  $display("c = %d", x3);
  b = x3;
  $display("c = %X (16)", b);
  $display("d = %d", x4);
  b = x4;
  $display("d = %X (16)", b);
  $display("e = %d", x5);
  b = x5;
  $display("e = %X (16)", b);
end // main
endmodule // Guia_0103