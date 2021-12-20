package modele;

import java.awt.*;
import java.lang.reflect.Field;

public class Parser {

    private Command<String> directory = new Command<>(
            "directory",
            "fractal.png",
            "Permet de choisir le nom du fichier (et son extension) dans lequel la fractal va être généré."
    );
    private Command<String> type = new Command<>(
            "type     ",
            "",
            "Permet de choisir le type de fractal à générer"
    );
    private Command<String> generator = new Command<>(
            "generator",
            "",
            "Choisir une methode de generation de fractal. Multi thread, single thread ou bien encore en ForkJoinPool"
    );
    private Command<Color> color = new Command<>(
            "color    ",
            new Color(255, 0, 0),
            "Choisir une couleurde base pour la colorisation de la fractal"
    );
    private Command<Integer> size = new Command<>(
            "size     ",
            2000,
            "Choisir la dimension de l'image en pixel"
    );

    public Command<Color> getColor() {
        return color;
    }

    public Command<String> getDirectory() {
        return directory;
    }

    public Command<String> getGenerator() {
        return generator;
    }

    public Command<Integer> getSize() {
        return size;
    }

    public Command<String> getType() {
        return type;
    }

    void checkArgs(String[] args) throws IllegalArgumentException { //TODO : Faire ses propres execptions
        for (String cmd : args) {
            String[] arr = cmd.split("=");
            if (!arr[0].equals("fractal") && arr.length != 1) throw new IllegalArgumentException();
            switch (arr[1]) {
                case "-d" -> directory.setParameter(arr[2]);
                case "-t" -> type.setParameter(arr[2]);
                case "-g" -> generator.setParameter(arr[2]);
                case "-c" -> {
                    try {
                        Field field = Class.forName("java.awt.Color").getField(arr[2]);
                        Color col = (Color) field.get(null);
                        color.setParameter(col);
                    } catch (Exception e) {
                        throw new IllegalArgumentException();
                    }
                }
                case "-s" -> {
                    try {
                        int foo = Integer.parseInt(arr[2]);
                        size.setParameter(foo);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "usage: fractal [-d=directory] [-t=type] [-g=generator] [-c=color] [-s=size]\n" + getDirectory() + "\n" + getType() + "\n" + getGenerator() + "\n" + getColor() + "\n" + getSize();
    }

    public class Command<param> {
        private final String name, description;
        private param parameter;

        public Command(String name, param parameter, String description) {
            this.name = name;
            this.parameter = parameter;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        public param getParameter() {
            return parameter;
        }

        public void setParameter(param parameter) {
            this.parameter = parameter;
        }

        @Override
        public String toString() {
            return "\t" + getName() + "\t" + getDescription();
        }
    }

}