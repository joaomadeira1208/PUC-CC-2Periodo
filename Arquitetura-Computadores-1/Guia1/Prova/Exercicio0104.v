
module Exercicio0104;
    //Dados
    reg[7:0] a = 0;
    reg[7:0] b = 8'b00011010;

    initial
        begin: main
            $display("Exercicio 4 - Guia 1");
            $display("Binário = %8b", b);
            a = b;
            $display("Base 4 = [%2b] [%2b] [%2b] [%2b] = %o%o%o%o", a[7:6], a[5:4], a[3:2], a[1:0], a[7:6], a[5:4], a[3:2], a[1:0] );
            $display("Base 8 = [%3b] [%3b] [%3b] = %o%o%o", b[7:6], b[5:3], b[2:0], b[7:6], b[5:3], b[2:0]);
            $display("Base 16 = [%4b] [%4b] = %x%x", b[7:4], b[3:0], b[7:4], b[3:0]);
        end
endmodule 