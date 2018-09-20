//@formatter:off
/**
 *  $$Id$$
 *       . * .
 *     * RRRR  *    Copyright (c) 2017 EUIPO: European Union Intellectual
 *   .   RR  R   .  Property Office (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 */
//@formatter:on
package com.jskno.persistence.listener;

import com.jskno.persistence.base.AbstractEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Jose on 18/11/17.
 */
public class EntityListener {

    @PrePersist
    public void onCreate(final AbstractEntity entity) {
        final String currentUserId = "SYSTEM"; //getCurrentUID();
        entity.setCreatedAt(new Date());
        entity.setCreatedByUser(currentUserId);
        entity.setUpdatedAt(new Date());
        entity.setUpdatedByUser(currentUserId);
    }

    @PreUpdate
    public void onUpdate(final AbstractEntity entity) {
        final LocalDateTime currentDate = LocalDateTime.now();
        final String currentUserId = "SYSTEM"; //getCurrentUID();
        entity.setUpdatedAt(new Date());
        entity.setUpdatedByUser(currentUserId);
    }


}
