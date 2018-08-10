package com.ten.controller.record;

import com.ten.controller.BaseController;
import com.ten.dto.ResponseResult;
import com.ten.manager.record.RecordServiceManager;
import com.ten.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ten.check.ControllerCheckUtil.*;

/**
 * record
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/record")
public class RecordController extends BaseController<RecordVO, ResponseResult> {

    private final RecordServiceManager recordServiceManager;

    @Autowired
    public RecordController(RecordServiceManager recordServiceManager) {
        this.recordServiceManager = recordServiceManager;
    }

    /**
     * listById
     * <p>
     * 获取用户所有Record信息
     *
     * @param userId user_id
     * @return list RecordVO
     */
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    @Override
    public ResponseResult listById(@PathVariable String userId) {
        // check
        checkRequestDataNotNull(userId);
        checkRequestDataFormatInt(userId);
        // execute
        int id = Integer.parseInt(userId);
        List<RecordVO> recordVOList = recordServiceManager.getUserRecordListByUserId(id);
        // return
        checkResourceNotNull(recordVOList);
        return new ResponseResult(recordVOList);
    }

    /**
     * create
     * <p>
     * 创建新的Record信息
     *
     * @param entity RecordVO
     * @return result
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ResponseResult add(@RequestBody RecordVO entity) {
        // check
        checkRequestDataNotNull(entity);
        // execute
        int result = recordServiceManager.createRecord(entity);
        // return
        checkExecuteResultSuccess(result);
        return new ResponseResult();
    }

    /**
     * update
     * <p>
     * 更新某条Record
     *
     * @param entity RecordVO
     * @return result
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public ResponseResult update(@RequestBody RecordVO entity) {
        // check
        checkRequestDataNotNull(entity);
        // execute
        int result = recordServiceManager.updateRecord(entity);
        // return
        checkExecuteResultSuccess(result);
        return new ResponseResult();
    }

    /**
     * deleteById
     * <p>
     * 删除某条Record
     *
     * @param recordId record_id
     * @return result
     */
    @RequestMapping(value = "/{recordId}", method = RequestMethod.DELETE)
    @Override
    public ResponseResult deleteById(@PathVariable String recordId) {
        // check
        checkRequestDataNotNull(recordId);
        checkRequestDataFormatInt(recordId);
        // execute
        int id = Integer.parseInt(recordId);
        int result = recordServiceManager.deleteRecordByPrimaryKey(id);
        // return
        checkExecuteResultSuccess(result);
        return new ResponseResult();
    }
}
