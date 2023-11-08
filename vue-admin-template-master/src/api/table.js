import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/system/sysRole',
    method: 'get',
    params
  })
}
