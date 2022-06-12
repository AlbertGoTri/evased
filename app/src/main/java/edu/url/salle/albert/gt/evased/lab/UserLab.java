package edu.url.salle.albert.gt.evased.lab;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.User;

public class UserLab {
    private ArrayList<User> users;
    private ArrayList<Conversation> conversations;

    public UserLab(){
        users = new ArrayList<>();
        conversations = new ArrayList<>();
        users.add(new User("Marcel", "Lopez", "elPikoDeOro@gmail.com", "password"));
        users.add(new User("Robert", "Ball", "elMorenoDeLavapies@hotmail.com", "password"));
        users.add(new User("Albert", "McCollum", "surmanoConPitis23@gmail.com", "password"));
        users.add(new User("Yousef", "Antetokoumpo", "deCallePalosAlPecho5@gmail.com", "password"));
        users.add(new User("Igor", "Morant", "calleEnElCorazon@gmail.com", "password"));
        users.add(new User("Sargento", "Bonilla", "SargentoBonilla@elDisparos.com", "password"));
        users.add(new User("Papi", "World", "MeteSaca@miProfesion.com", "password"));


        conversations.add(new Conversation(users.get(0), users.get(1)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(0));
        conversations.get(0).addMessage("Que dices de mih muertoh cara Serpiente?", users.get(1));
        conversations.get(0).addMessage("Lo que oyes Surmanito XDDD", users.get(0));
        conversations.get(0).addMessage("Ah, y dame el trabajo de Geografia que sino te cojo de los pelos..", users.get(0));
        conversations.get(0).addMessage("Ahora mismo te lo doy figura, dame tu mail", users.get(1));
        conversations.get(0).addMessage("elPikoDeOro@gmail.com", users.get(0));
        conversations.get(0).addMessage("Enviado toooooooonto", users.get(1));
        conversations.get(0).addMessage("Gracias caballero", users.get(0));
        conversations.get(0).addMessage("Cambialo que sacan copia eh", users.get(1));
        conversations.get(0).addMessage("Como te pillen te rompo el coxis", users.get(1));
        conversations.get(0).addMessage("Hmmm", users.get(0));
        conversations.get(0).addMessage("Si", users.get(0));

        conversations.add(new Conversation(users.get(0), users.get(2)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(0));
        conversations.get(0).addMessage("Que dices de mih muertoh cara Serpiente?", users.get(2));

        conversations.add(new Conversation(users.get(0), users.get(3)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(3));
        conversations.get(0).addMessage("Que dices de mih muertoh cara Serpiente?", users.get(0));

        conversations.add(new Conversation(users.get(0), users.get(4)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(0));
        conversations.get(0).addMessage("Ca**************brooooooooooooooonnnnnnnnnnnn", users.get(0));

        conversations.add(new Conversation(users.get(0), users.get(5)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(5));
        conversations.get(0).addMessage("Que dices de mih muertoh cara Serpiente?", users.get(0));

        conversations.add(new Conversation(users.get(0), users.get(6)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(6));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(6));

        conversations.add(new Conversation(users.get(1), users.get(6)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(6));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(1));

        conversations.add(new Conversation(users.get(2), users.get(6)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(6));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(2));

        conversations.add(new Conversation(users.get(3), users.get(4)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(4));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(3));

        conversations.add(new Conversation(users.get(4), users.get(2)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(2));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(4));

        conversations.add(new Conversation(users.get(6), users.get(3)));
        conversations.get(0).addMessage("Tus muertoh!", users.get(6));
        conversations.get(0).addMessage("CUIDADOOOO, que no te vea yo por ahii", users.get(3));

    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
