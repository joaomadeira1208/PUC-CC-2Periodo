
module Exercicio0103;
    integer x = 26;
    reg[7:0] b = 0;

    initial 
        begin: main
            $display("Exercicio 3 - Guia 1");
            $display("x = %d", x);
            $display("b = %8b", b);
            b = x;

            $display("b = %B (2)", b);
            $display("b = [%2b] [%2b] [%2b] [%2b] = %o%o%o%o (4)", b[7:6], b[5:4], b[3:2], b[1:0], b[7:6], b[5:4], b[3:2], b[1:0] );
            $display("b = %o (8)", b);
            $display("b = %x (16)", b);
            $display("b = %X (16)", b);

        end
endmodule