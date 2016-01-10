package artopia.command.infrastructure.move;

/**
 * @author Vehsamrak
 */
public enum Direction
{
    NORTH {
        public String toString()
        {
            return "north";
        }
    },

    EAST {
        public String toString()
        {
            return "east";
        }
    },

    SOUTH {
        public String toString()
        {
            return "south";
        }
    },

    WEST {
        public String toString()
        {
            return "west";
        }
    },

    UP {
        public String toString()
        {
            return "up";
        }
    },

    DOWN {
        public String toString()
        {
            return "down";
        }
    };

    public abstract String toString();
}
