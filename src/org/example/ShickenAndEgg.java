//Сделано с помощью ИИ "DeepSeek"
package org.example;

class ChickenAndEgg {


    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        displayHeader();

        EggVoice eggThread = new EggVoice();

        System.out.println(CYAN + BOLD + "\nЧто появилось первым: курица или яйцо?" + RESET);
        System.out.println(YELLOW + "═".repeat(50) + RESET);
        System.out.println(PURPLE + "🎭 Спор начат!" + RESET);
        System.out.println();

        eggThread.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Восстанавливаем прерванный статус
                Thread.currentThread().interrupt();
                System.out.println(RED + "Главный поток был прерван!" + RESET);
                break;
            }

            displayChickenMessage(i + 1);
        }

        System.out.println();
        displayResult(eggThread);

        System.out.println(YELLOW + "═".repeat(50) + RESET);
        System.out.println(PURPLE + BOLD + "🏁 Спор окончен!" + RESET);
    }

    private static void displayHeader() {
        System.out.println(GREEN + "╔══════════════════════════════════════════════════╗");
        System.out.println("║         ВЕЧНЫЙ СПОР: КУРИЦА ИЛИ ЯЙЦО?         ║");
        System.out.println("║           (демонстрация потоков)              ║");
        System.out.println("╚══════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    private static void displayChickenMessage(int iteration) {
        String chickenEmoji = "🐔";
        String number = String.format("%02d", iteration);

        System.out.print(BLUE + BOLD + "[Курица " + number + "] " + RESET);
        System.out.print(chickenEmoji + " ");


        for (int i = 0; i < iteration % 4; i++) {
            System.out.print(".");
        }


        for (int i = iteration % 4; i < 3; i++) {
            System.out.print(" ");
        }

        System.out.println(" " + YELLOW + "\"Курица!\"" + RESET);
    }

    private static void displayResult(EggVoice eggThread) {
        System.out.println(CYAN + "══════════════ ПОДВОДИМ ИТОГИ ══════════════" + RESET);

        if (eggThread.isAlive()) {
            System.out.println(YELLOW + "⏳ Ожидание завершения потока яйца..." + RESET);

            try {
                eggThread.join();
                System.out.println();
                System.out.println(GREEN + BOLD + "🥚 Итог: Первым появилось ЯЙЦО!" + RESET);
                System.out.println(PURPLE + "   (Поток яйца завершился позже)" + RESET);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(RED + "Ошибка при ожидании завершения потока!" + RESET);
            }
        } else {
            System.out.println();
            System.out.println(GREEN + BOLD + "🐔 Итог: Первой появилась КУРИЦА!" + RESET);
            System.out.println(PURPLE + "   (Поток яйца завершился раньше)" + RESET);
        }

        // Статистика
        System.out.println();
        System.out.println(CYAN + "📊 Статистика:" + RESET);
        System.out.println("   • Главный поток: 10 сообщений 'Курица'");
        System.out.println("   • Поток яйца: 10 сообщений 'Яйцо'");
        System.out.println("   • Время выполнения: ~10 секунд");
    }
}

class EggVoice extends Thread {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BOLD = "\u001B[1m";

    @Override
    public void run() {
        System.out.println(GREEN + "🔄 Поток 'Яйцо' запущен!" + RESET);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // Восстанавливаем прерванный статус
                Thread.currentThread().interrupt();
                System.out.println(GREEN + "Поток 'Яйцо' был прерван!" + RESET);
                break;
            }

            displayEggMessage(i + 1);
        }

        System.out.println(GREEN + "✅ Поток 'Яйцо' завершил работу!" + RESET);
    }

    private void displayEggMessage(int iteration) {
        String eggEmoji = "🥚";
        String number = String.format("%02d", iteration);

        System.out.print(GREEN + BOLD + "[Яйцо   " + number + "] " + RESET);
        System.out.print(eggEmoji + " ");


        for (int i = 0; i < iteration % 4; i++) {
            System.out.print(".");
        }


        for (int i = iteration % 4; i < 3; i++) {
            System.out.print(" ");
        }

        System.out.println(" " + YELLOW + "\"Яйцо!\"" + RESET);
    }
}