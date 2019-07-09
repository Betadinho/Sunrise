TODO: ---------------------------------- TODO --------------------------------------

IMPORTANT:

When accessing or iterating over <List>entities (or handler.entities):
Synchronize for thread safety. Will through Exception and stop executing otherwise!

Example:  - Tick Method in entity.handler.Handler

            public void tick() {
                synchronized(entities) {
                    for(Entity tempEntity : entities) {
                        tempEntity.tick();
                    }
                }
                }
