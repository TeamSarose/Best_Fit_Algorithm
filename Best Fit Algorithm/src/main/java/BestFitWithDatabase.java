import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class BestFitWithDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Priority Queue for memory blocks (sorted by size)
        PriorityQueue<MemoryBlock> memoryBlocks = new PriorityQueue<>(Comparator.comparingInt(MemoryBlock::getSize));

        // Input memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.print("Enter size for block " + (i + 1) + ": ");
            memoryBlocks.add(new MemoryBlock(i + 1, scanner.nextInt()));
        }

        // Input processes
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size for process " + (i + 1) + ": ");
            processes.add(ProcessFactory.createProcess(i + 1, scanner.nextInt()));
        }

        // Allocate memory
        Connection connection = DatabaseConnection.getConnection();
        for (Process process : processes) {
            MemoryBlock bestBlock = null;
            for (MemoryBlock block : memoryBlocks) {
                if (block.getSize() >= process.getSize()) {
                    if (bestBlock == null || block.getSize() < bestBlock.getSize()) {
                        bestBlock = block;
                    }
                }
            }

            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO MemoryAllocationLog (process_id, process_size, block_id, timestamp) VALUES (?, ?, ?, NOW())"
                );
                statement.setInt(1, process.getId());
                statement.setInt(2, process.getSize());

                if (bestBlock != null) {
                    bestBlock.allocate(process.getSize());
                    memoryBlocks.remove(bestBlock);
                    memoryBlocks.add(bestBlock);
                    statement.setInt(3, bestBlock.getId());
                } else {
                    statement.setNull(3, java.sql.Types.INTEGER);
                }

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Display allocation
        System.out.println("\nProcess Allocation:");
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MemoryAllocationLog");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Process ID: " + resultSet.getInt("process_id") +
                        ", Size: " + resultSet.getInt("process_size") +
                        ", Block ID: " + (resultSet.getObject("block_id") != null ? resultSet.getInt("block_id") : "Not Allocated"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

