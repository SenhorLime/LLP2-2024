class Prova {

    public static void main(String[] args) {
        new Prova();
    }

    public Prova() {

        System.out.println("Controle de astros");
        Astro[] astro = new Astro[6];

        String nome = "João Vitor"; //Troque pelo seu nome!

        astro[0] = new Lua(nome, "Saturno");
        astro[1] = new Lua("Io", "Jupter");
        astro[2] = new Estrela("Sol", "Sistema Solar");
        astro[3] = new Estrela("Sirius A", "Sirius");
        astro[4] = new BuracoNegro("Cyg X-1", 15);
        astro[5] = new BuracoNegro("HDE 226868", 30);

        System.out.println("Nome do astro na posição 0 = " + astro[0].nome);

        if (astro[5] instanceof BuracoNegro) {
            BuracoNegro aux = (BuracoNegro) astro[5];
            System.out.println("Massa do buraco negro " +
            aux.nome + " = " + aux.massasDoSol + " assas solares");
        }

        System.out.println("Lista de astros:");
        for (int i = 0; i < astro.length; i++) {
            System.out.println(astro[i]); // astro[i].toString();
        }

    }
}


