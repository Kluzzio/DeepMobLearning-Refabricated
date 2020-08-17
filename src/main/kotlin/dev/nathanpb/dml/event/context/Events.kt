/*
 * Copyright (C) 2020 Nathan P. Bombana, IterationFunk
 *
 * This file is part of Deep Mob Learning: Refabricated.
 *
 * Deep Mob Learning: Refabricated is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Deep Mob Learning: Refabricated is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Deep Mob Learning: Refabricated.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.nathanpb.dml.event.context

import dev.nathanpb.dml.utils.event
import net.minecraft.entity.player.PlayerEntity

val LivingEntityDamageEvent = event<(LivingEntityDamageContext)->LivingEntityDamageContext?> { listeners ->
    { context: LivingEntityDamageContext ->
        listeners.fold(context) { acc, listener ->
            listener(acc) ?: acc
        }
    }
}

// Doing it just with PlayerEntity to prevent server overload
val PlayerEntityTickEvent = event<(PlayerEntity)->Unit> { listeners ->
    { entity ->
        listeners.forEach {
            it(entity)
        }
    }
}
