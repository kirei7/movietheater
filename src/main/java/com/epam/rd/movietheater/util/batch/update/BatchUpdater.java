package com.epam.rd.movietheater.util.batch.update;

import java.util.List;

public interface BatchUpdater <T, Dto> {
    List<T> updateWithData(List<Dto> data);
}
