package com.netflix.astyanax.connectionpool.impl;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.netflix.astyanax.connectionpool.HostConnectionPool;

public interface Topology<CL> {
    /**
     * Refresh the internal topology structure
     * 
     * @param ring
     * @return
     */
    boolean setPools(Collection<HostConnectionPool<CL>> ring);

    /**
     * Add a pool without knowing its token. This pool will be added to the all
     * pools partition only
     * 
     * @param pool
     */
    void addPool(HostConnectionPool<CL> pool);

    /**
     * Remove this pool from all partitions
     * 
     * @param pool
     */
    void removePool(HostConnectionPool<CL> pool);

    /**
     * Resume a host that was previously down
     * 
     * @param pool
     */
    void resumePool(HostConnectionPool<CL> pool);

    /**
     * Suspend a host that is down
     * 
     * @param pool
     */
    void suspendPool(HostConnectionPool<CL> pool);

    /**
     * Refresh the internal state and apply the latency score strategy
     */
    void refresh();

    /**
     * Get the partition best suited to handle a row key
     * 
     * @param token
     * @return
     */
    TokenHostConnectionPoolPartition<CL> getPartition(ByteBuffer rowkey);
    
    /**
     * TODO 
     * 
     * Get the partition best suited for handling a set of row keys
     * @param tokens
     * @return
     */
    // HostConnectionPoolPartition<CL> getPartition(Collection<ByteBuffer> tokens);

    /**
     * Return a partition that represents all hosts in the ring
     * 
     * @return
     */
    TokenHostConnectionPoolPartition<CL> getAllPools();

    /**
     * Get total number of tokens in the topology
     * 
     * @return
     */
    int getPartitionCount();

    /**
     * Return a list of all unqiue ids or first token for partitions in the topology
     * @return
     */
    List<String> getPartitionNames();

    /**
     * Return a mapping of partition ids to partition details
     * @return
     */
    Map<String, TokenHostConnectionPoolPartition<CL>> getPartitions();
    
    /**
     * Return the partition for a specific token
     * @param token
     * @return
     */
    TokenHostConnectionPoolPartition<CL> getPartition(String token);
}
