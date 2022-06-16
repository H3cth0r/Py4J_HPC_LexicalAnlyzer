from py4j.java_gateway import JavaGateway
gateway = JavaGateway()
print("connected")

txt_input = """
program ProgramaEjemplo
var
    perro, gato : int;
    begin
        perro = 2 + 2;
            read gato;
                write 2.2 + 3;
                end

                program programaejemplo\nvar\n\tperro, gato : int;\nbegin\n\tperro = 2 + 2;\n\tread gato;\n\twrite 2.2 + 3;\nend\0
"""

tokens = gateway.entry_point.list_of_tokens(txt_input)

print(tokens)
print(len(tokens))
for i in range(len(tokens)):
    print(tokens[i].get_kind())
    print(tokens[i].get_color())
    print("===================")


