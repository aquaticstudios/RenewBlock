package team.aquatic.studios.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import team.aquatic.studios.RenewBlock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockListener implements Listener {

    private final Map<String, Long> lastRegenTime = new HashMap<>();
    private final Map<String, Material> originalBlockType = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();
        final Material type = block.getType();
        final World world = block.getWorld();
        final Player player = event.getPlayer();

        if (isRegenerable(type, world.getName())) {
            event.setCancelled(true);

            final int x = block.getX();
            final int y = block.getY();
            final int z = block.getZ();

            ItemStack drop = getDropFromOre(type);

            if (RenewBlock.getInstance().getConfig().getBoolean("drop-ore")) {
                world.dropItemNaturally(block.getLocation(), drop);
            } else {
                player.getInventory().addItem(drop);
            }

            originalBlockType.putIfAbsent(getBlockKey(x, y, z), type);

            block.setType(Material.AIR);

            if (RenewBlock.getInstance().getConfig().getBoolean("block_replace.switch")) {
                Material replacementBlock = Material.getMaterial(RenewBlock.getInstance().getConfig().getString("block_replace.block"));
                if (replacementBlock != null) {
                    world.getBlockAt(x, y, z).setType(replacementBlock);
                }
            }

            String worldName = world.getName();
            int regenDelay = RenewBlock.getInstance().getConfig().getInt("time-regen") * 20;
            long currentTime = System.currentTimeMillis();
            if (!lastRegenTime.containsKey(worldName) || currentTime - lastRegenTime.get(worldName) >= regenDelay) {
                lastRegenTime.put(worldName, currentTime);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.getBlockAt(x, y, z).setType(originalBlockType.get(getBlockKey(x, y, z)));
                    }
                }.runTaskLater(RenewBlock.getInstance(), regenDelay);
            }
        }
    }

    private boolean isRegenerable(Material type, String worldName) {
        List<String> enabledWorlds = RenewBlock.getInstance().getConfig().getStringList("enabled_worlds");
        if (!enabledWorlds.contains(worldName)) {
            return false;
        }

        List<String> regenerableBlocks = RenewBlock.getInstance().getConfig().getStringList("regenerable_blocks");
        return regenerableBlocks.contains(type.toString());
    }

    private ItemStack getDropFromOre(Material oreType) {
        switch (oreType) {
            case COAL_ORE:
                return new ItemStack(Material.COAL);
            case DIAMOND_ORE:
                return new ItemStack(Material.DIAMOND);
            case EMERALD_ORE:
                return new ItemStack(Material.EMERALD);
            case COPPER_ORE:
                return new ItemStack(Material.COPPER_INGOT);
            case LAPIS_ORE:
                return new ItemStack(Material.LAPIS_LAZULI);
            case NETHER_QUARTZ_ORE:
                return new ItemStack(Material.QUARTZ);
            case REDSTONE_ORE:
                return new ItemStack(Material.REDSTONE);
            case IRON_ORE:
                return new ItemStack(Material.IRON_INGOT);
            case GOLD_ORE:
                return new ItemStack(Material.GOLD_INGOT);
            default:
                return new ItemStack(Material.AIR);
        }
    }

    private String getBlockKey(int x, int y, int z) {
        return x + "_" + y + "_" + z;
    }
}
