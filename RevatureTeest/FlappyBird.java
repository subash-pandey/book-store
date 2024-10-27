import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int PIPE_WIDTH = 60;
    private static final int PIPE_GAP = 150;
    private static final int BIRD_SIZE = 30;
    private static final int GRAVITY = 1;
    private static final int JUMP_VELOCITY = -10;

    private Timer timer;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private int score;
    private boolean gameOver;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird();
        pipes = new ArrayList<>();
        score = 0;
        gameOver = false;

        timer = new Timer(20, this);
        timer.start();

        addPipes();
    }

    private void addPipes() {
        Random random = new Random();
        int pipeHeight = random.nextInt(HEIGHT / 2 - PIPE_GAP) + PIPE_GAP / 2;
        pipes.add(new Pipe(WIDTH, pipeHeight));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw sky
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw bird
        bird.draw(g);

        // Draw pipes
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);

        // Draw game over message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.update();

                if (pipe.intersects(bird)) {
                    gameOver = true;
                }

                if (pipe.isOffScreen()) {
                    pipes.remove(i);
                    i--;
                    score++;
                }
            }

            if (bird.getY() + BIRD_SIZE > HEIGHT) {
                gameOver = true;
            }

            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < WIDTH - 200) {
                addPipes();
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            bird.jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class Bird {
        private int x;
        private int y;
        private int velocity;

        public Bird() {
            x = 50;
            y = HEIGHT / 2;
            velocity = 0;
        }

        public void update() {
            velocity += GRAVITY;
            y += velocity;
        }

        public void jump() {
            velocity = JUMP_VELOCITY;
        }

        public void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, BIRD_SIZE, BIRD_SIZE);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, BIRD_SIZE, BIRD_SIZE);
        }
    }

    class Pipe {
        private int x;
        private int topHeight;
        private int speed;

        public Pipe(int x, int topHeight) {
            this.x = x;
            this.topHeight = topHeight;
            speed = 5;
        }

        public void update() {
            x -= speed;
        }

        public void draw(Graphics g) {
            g.setColor(Color.GREEN);
            g.fillRect(x, 0, PIPE_WIDTH, topHeight);
            g.fillRect(x, topHeight + PIPE_GAP, PIPE_WIDTH, HEIGHT - topHeight - PIPE_GAP);
        }

        public boolean intersects(Bird bird) {
            Rectangle birdBounds = bird.getBounds();
            Rectangle topPipeBounds = new Rectangle(x, 0, PIPE_WIDTH, topHeight);
            Rectangle bottomPipeBounds = new Rectangle(x, topHeight + PIPE_GAP, PIPE_WIDTH, HEIGHT - topHeight - PIPE_GAP);
            return birdBounds.intersects(topPipeBounds) || birdBounds.intersects(bottomPipeBounds);
        }

        public boolean isOffScreen() {
            return x + PIPE_WIDTH < 0;
        }

        public int getX() {
            return x;
        }
    }
}