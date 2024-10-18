public class MM1 {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java MM1 <lambda> <mu> <duration> <debug>");
            System.exit(1);
        }
        double lambda = Double.parseDouble(args[0]);
        double mu = Double.parseDouble(args[1]);
        double duration = Double.parseDouble(args[2]);
        int debug = Integer.parseInt(args[3]);



        // Create ech and run the simulation and print result
        Ech ech = new Ech(lambda, mu);
        ech.simulation(duration,debug);





    }
}
//struct donné permettant de classé toute les event (linkedlist)
// fait appel a fct de stat qui sont dans des variable de l'objet stat
// var debug si 1 on affiche tout si 0 juste stat à la fin
// 10 million unité de temps = max 22s sur turing
// Rejeux (voir cours) moyenne avec intervalle de confiance à discuter
// résultat de statistique pour montrer que c'est pertinent où non
// COmpléxité de notre prog (est il bien oui / non pq ?)
// les limites que se passe t'il si lambda = NU